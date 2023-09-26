package pl.zakrzewski.juniorjavajoboffers.domain.emailsender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.zakrzewski.juniorjavajoboffers.domain.emailsender.exceptions.ConfirmedUsersNotFound;
import pl.zakrzewski.juniorjavajoboffers.domain.emailsender.exceptions.OffersNotFound;
import pl.zakrzewski.juniorjavajoboffers.domain.offer.OfferFacade;
import pl.zakrzewski.juniorjavajoboffers.domain.offer.dto.OfferDto;
import pl.zakrzewski.juniorjavajoboffers.domain.register.RegisterFacade;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EmailSenderFacade {
    private final EmailSenderService emailSenderService;
    private final RegisterFacade registerFacade;
    private final OfferFacade offerFacade;

    public void sendConfirmationEmail(String email, String token) {
        emailSenderService.sendConfirmationEmail(email, token);
    }

    //scheduler
    public void sendJobOffersEmail() {
        List<OfferDto> offers = offerFacade.fetchAllOffersSaveAllIfNotExists();
        List<String> emails = registerFacade.findEmailsOfConfirmedUsers();
        if (emails.isEmpty()) {
            throw new ConfirmedUsersNotFound();
        }
        if (offers.isEmpty()) {
            throw new OffersNotFound();
        }
        emailSenderService.sendOffersEmail(emails, offers);
    }

}
