/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.andonsystem.service;

import in.andonsystem.entity.Section;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import in.andonsystem.repository.SectionRepository;

/**
 *
 * @author Md Zahid Raza
 */
@Service
@Transactional
public class SectionService{

   @Autowired
   private SectionRepository sectionRepository;
   
   public List<Section> findAll() {
      return sectionRepository.findAll();
   }
   
}
