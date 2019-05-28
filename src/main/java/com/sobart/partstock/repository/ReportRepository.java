package com.sobart.partstock.repository;

import com.sobart.partstock.domain.Part;
import com.sobart.partstock.domain.Report;
import com.sobart.partstock.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findByUserReportAndPartReport(User user, Part part);
}
