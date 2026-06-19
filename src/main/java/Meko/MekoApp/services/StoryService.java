package Meko.MekoApp.services;

import Meko.MekoApp.entities.Story;
import Meko.MekoApp.repositories.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoryService {
    private StoryRepository storyRepository;

    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }
    public List<Story> findAll(){
        return storyRepository.findAll();
    }
}
