package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Camada de Serviço do Museum.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  private final MuseumFakeDatabase museumFakeDatabase;

  @Autowired
  public MuseumService(MuseumFakeDatabase  museumFakeDatabase) {
    this.museumFakeDatabase = museumFakeDatabase;
  }

  @Override
  public Museum createMuseum(Museum museum) {

    if (CoordinateUtil.isCoordinateValid((museum.getCoordinate()))) {
      return this.museumFakeDatabase.saveMuseum(museum);
    } else {
      throw new InvalidCoordinateException();
    }
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    boolean isValidCoordinate = CoordinateUtil.isCoordinateValid(coordinate);
    if (!isValidCoordinate) {
      throw new InvalidCoordinateException();
    }
    Optional<Museum> closesMuseum = museumFakeDatabase.getClosestMuseum(coordinate, maxDistance);
    if (closesMuseum.isEmpty()) {
      throw new MuseumNotFoundException();
    }
    return closesMuseum.get();
  }

  @Override
  public Museum getMuseum(Long id) {
    return null;
  }
}
