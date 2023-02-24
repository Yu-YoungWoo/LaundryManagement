package project.laundry.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.laundry.repository.VisitRepository;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

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

    public List<Integer> getTotalRevenueByMonth() {
        int[] month = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        List<Integer> revenueList = new ArrayList<>();
        for(int i : month) {
            revenueList.add(repository.getTotalRevenueByMonth(i));

        }

        return revenueList;
    }

}
