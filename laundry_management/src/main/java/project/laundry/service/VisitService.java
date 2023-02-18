package project.laundry.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.repository.VisitRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.TemporalAdjusters;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitService {

    private final VisitRepository repository;

    LocalDate currentDate = LocalDate.now();

    /**
     * 당월/당년 방문자 수 통계 메소드
     */
    public Long getVisitsInMonth() {
        LocalDate startOfMonth = currentDate.withDayOfMonth(1);
        LocalDate endOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());

        return repository.getVisitsInMonth(startOfMonth, endOfMonth);
    }
    public Long getVisitsInYear() {
        LocalDate startOfYear = currentDate.withDayOfYear(1);
        LocalDate endOfYear = currentDate.withDayOfYear(currentDate.lengthOfYear());

        return repository.getVisitsInYear(startOfYear, endOfYear);
    }


    /**
     * 당월/당년 수입 통계 메소드
     */

    public Long getRevenueInMonth() {
        LocalDate startOfMonth = currentDate.withDayOfMonth(1);
        LocalDate endOfMonth = currentDate.with(TemporalAdjusters.lastDayOfMonth());

        return repository.getRevenueInMonth(startOfMonth, endOfMonth);
    }
    public Long getRevenueInYear() {
        LocalDate startOfYear = currentDate.withDayOfYear(1);
        LocalDate endOfYear = currentDate.withDayOfYear(currentDate.lengthOfYear());

        return repository.getRevenueInYear(startOfYear, endOfYear);
    }
}
