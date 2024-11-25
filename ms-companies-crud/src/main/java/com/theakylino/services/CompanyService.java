package com.theakylino.services;

import com.theakylino.entities.Company;

public interface CompanyService {
  Company create(Company company);
  Company readByName(String name);
  Company update(Company company, String name);
  void  delete(String name);
}
