package Meko.Meko.services;

import Meko.Meko.entities.Story;
import Meko.Meko.repositories.StoryRepository;
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
