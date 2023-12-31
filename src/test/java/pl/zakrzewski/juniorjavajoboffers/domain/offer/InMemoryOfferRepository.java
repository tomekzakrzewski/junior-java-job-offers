package pl.zakrzewski.juniorjavajoboffers.domain.offer;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.StreamSupport;

public class InMemoryOfferRepository implements OfferRepository {

    Map<String, Offer> database = new ConcurrentHashMap<>();
    @Override
    public boolean existsOfferByCompanyAndPosted(String company, Long posted) {
        long count = database.values()
                .stream()
                .filter(offer -> offer.getCompany().equals(company) && offer.getPosted().equals(posted))
                .count();
        return count == 1;
    }

    @Override
    public Optional<List<Offer>> getOffersByCreatedDate(LocalDate date) {
        List<Offer> offers = database.values()
                .stream()
                .filter(offer -> offer.getCreatedDate().equals(date))
                .toList();
        return Optional.of(offers);
    }

    @Override
    public <S extends Offer> S save(S entity) {
        if (database.values().stream().anyMatch(offer -> offer.equals(entity))) {
            throw new DuplicateKeyException(String.format("Offer from [%s] already exists", entity.getCompany()));
        }
        UUID id = UUID.randomUUID();

        Offer offer = Offer.builder()
                .id(id.toString())
                .company(entity.getCompany())
                .position(entity.getPosition())
                .salary(entity.getSalary())
                .url(entity.getUrl())
                .city(entity.getCity())
                .remote(entity.isRemote())
                .createdDate(entity.getCreatedDate())
                .build();

        database.put(offer.getId(), offer);
        return (S) offer;
    }

    @Override
    public <S extends Offer> List<S> saveAll(Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::save)
                .toList();
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Offer> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Offer> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<String> strings) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Offer getOne(String s) {
        return null;
    }

    @Override
    public Offer getById(String s) {
        return null;
    }

    @Override
    public Offer getReferenceById(String s) {
        return null;
    }

    @Override
    public <S extends Offer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Offer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Offer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Offer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Offer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }


    @Override
    public Optional<Offer> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Offer> findAll() {
        return null;
    }

    @Override
    public List<Offer> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Offer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Offer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Offer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findAll(Pageable pageable) {
        return null;
    }

}
