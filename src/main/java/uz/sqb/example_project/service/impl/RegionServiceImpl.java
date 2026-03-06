package uz.sqb.example_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sqb.example_project.repository.RegionRepository;
import uz.sqb.example_project.service.RegionService;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

}
