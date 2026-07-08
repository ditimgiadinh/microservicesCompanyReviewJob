package com.embarkx.firstjobapp.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean updateCompany(Company company, Long id);

    void createComapny(Company company);
    boolean deleteCompanyById(Long id);
    Company getCompanyById(Long id);
}
