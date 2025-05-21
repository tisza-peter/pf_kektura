package hu.palferi.kektura.kektura_volangeodata_downloader.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.palferi.kektura.kektura_volangeodata_downloader.entity.StopTimeEntity;

public interface StopTimeRepository extends JpaRepository<StopTimeEntity, UUID> {

}
