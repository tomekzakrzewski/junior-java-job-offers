package pl.zakrzewski.juniorjavajoboffers.infrastructure.offer.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.zakrzewski.juniorjavajoboffers.domain.offer.OfferFacade;

@Component
@AllArgsConstructor
@Log4j2
public class JobOffersEmailScheduler {
    private final OfferFacade offerFacade;

    @Scheduled(cron = "${offer.sendEmailOcurrence}")
    public void sendJobOffers() {
        offerFacade.sendEmailWithJobOffers();
        log.info("sending job offers");
    }

    @Scheduled(cron = "${offer.fetchOffersOcurrence}")
    public void fetchJobOffers() {
        offerFacade.fetchAllOffersSaveAllIfNotExists();
    }
}
