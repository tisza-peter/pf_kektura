package hu.palferi.kektura.kektura_kekturageodata_downloader.repository;

   
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.palferi.kektura.kektura_kekturageodata_downloader.entity.GpxLinkEntity;

@Repository
public interface GpxLinkRepository extends JpaRepository<GpxLinkEntity, Long> {
}
