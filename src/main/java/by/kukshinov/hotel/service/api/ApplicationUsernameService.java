package by.kukshinov.hotel.service.api;

import by.kukshinov.hotel.exceptions.ServiceException;
import by.kukshinov.hotel.model.ApplicationUsernameDto;

import java.util.List;

public interface ApplicationUsernameService {
    List<ApplicationUsernameDto> findRange(int startFrom, int finishWith) throws ServiceException;
}
