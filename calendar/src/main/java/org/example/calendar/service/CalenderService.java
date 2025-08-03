package org.example.calendar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.calendar.dto.CalenderRequestDto;
import org.example.calendar.dto.CalenderResponseDto;
import org.example.calendar.entity.CalenderEntity;
import org.example.calendar.repository.CalenderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalenderService {
    private CalenderRepository calenderRepository;

    // Create 메서드
    @Transactional
    public CalenderResponseDto createCalender(CalenderRequestDto calenderRequestDto) {
        CalenderEntity calenderEntity = new CalenderEntity(calenderRequestDto.getTitle(), calenderRequestDto.getContent(), calenderRequestDto.getName(), calenderRequestDto.getPassword());
        CalenderEntity savedCalenderEntity = calenderRepository.save(calenderEntity);
        return new CalenderResponseDto(savedCalenderEntity);

    }
}
