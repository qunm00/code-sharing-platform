package codesharingplatform;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CodeRepository extends CrudRepository<Code, UUID> {
    @Override
    @Query(value = "select * from code " +
            "where id = ?1 ", nativeQuery = true)
    Optional<Code> findById(UUID id);

    @Query(value = "select * from code " +
            "where time_expirable = false and views_expirable = false " +
            "order by date desc " +
            "limit 10", nativeQuery = true)
    List<Code> findLatest10();

    @Transactional
    @Modifying
    @Query(value = "update code " +
            "set views = views - 1 " +
            "where id = ?1 and views_expirable = true and views >= 0", nativeQuery = true)
    void updateViews(UUID id);
}