package com.theakylino.services;


import com.theakylino.entities.Category;
import com.theakylino.entities.Company;
import com.theakylino.repositories.CompanyRepository;
import jakarta.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyRepository companyRepository;

  @Override
  public Company create(Company company) {
    company.getWebSites().stream()
        .filter(webSite -> Objects.isNull(webSite.getCategory()))
        .forEach(webSite -> webSite.setCategory(Category.NONE));
    return this.companyRepository.save(company);
  }

  @Override
  public Company readByName(String name) {
    return Optional.ofNullable(name)
        .flatMap(companyRepository::findByName)
        .orElseThrow(() -> new NoSuchElementException("Company not found"));
  }

  @Override
  public Company update(Company company, String name) {
    return this.companyRepository.findByName(name)
        .map(companyToUpdate -> {
          companyToUpdate.setName(company.getName());
          companyToUpdate.setLogo(company.getLogo());
          companyToUpdate.setFoundationDate(company.getFoundationDate());
          companyToUpdate.setFounder(company.getFounder());
          return this.companyRepository.save(companyToUpdate);
        })
        .orElseThrow(() -> new NoSuchElementException("Company not found"));
  }

  @Override
  public void delete(String name) {
    this.companyRepository.findByName(name)
        .ifPresentOrElse(
            this.companyRepository::delete,
            () -> {
              throw new NoSuchElementException("Company not found");
            }
        );
  }

}
