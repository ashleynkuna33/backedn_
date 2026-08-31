package com.uwc_cam_champion.backend.services.Module;


import org.springframework.stereotype.Service;

import com.uwc_cam_champion.backend.exceptions.ResourceNotFoundException;
import com.uwc_cam_champion.backend.models.ModuleInfo;
import com.uwc_cam_champion.backend.models.Task;
import com.uwc_cam_champion.backend.models.User;
import com.uwc_cam_champion.backend.repositories.UserRepository;
import com.uwc_cam_champion.backend.repositories.ModuleInfoRepository;
import com.uwc_cam_champion.backend.repositories.TaskRepository;
import com.uwc_cam_champion.backend.request.moduleinfo.*;


@Service
public class ModuleInfoService implements IModuleInfoService{

    private final ModuleInfoRepository moduleInfoRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;


    public ModuleInfoService(ModuleInfoRepository moduleInfoRepository,UserRepository userRepository,TaskRepository taskRepository){

        this.moduleInfoRepository = moduleInfoRepository;
        this.userRepository =userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public ModuleInfo addModule(Long creatorId,AddModuleRequest request) {

        User user = userRepository.findById(creatorId).orElseThrow(() -> new ResourceNotFoundException("User not found with creatorId :" +creatorId));


        ModuleInfo module = new ModuleInfo(); 

        applyModuleInfo(module,  request);

        module.setCreator(user);


        return moduleInfoRepository.save(module);
    }



    @Override
    public ModuleInfo updateModule(Long moduleId, UpdateModuleRequest request) {

        ModuleInfo moduleInfo = moduleInfoRepository.findById(moduleId).orElseThrow(() -> new ResourceNotFoundException("Module not found with moduleId :" +moduleId));

        
        if(!moduleInfo.getId().equals(moduleId)){
            throw new IllegalArgumentException("Task does not belong in this module  with moduleId :" + moduleId);
        }

        applyModuleInfo(moduleInfo, request);

        return moduleInfoRepository.save(moduleInfo);

    }

    @Override
    public void deleteModule(Long moduleId, DeleteModuleRequest request ) { 
       
        ModuleInfo moduleInfo = moduleInfoRepository.findById(moduleId).orElseThrow(() -> new ResourceNotFoundException(" ModuleInfo not found with moduleId :"+ moduleId));
        
        moduleInfoRepository.delete(moduleInfo);
    }

    @Override
    public Task addTask(Long moduleId, AddTaskRequest request) {
        
        ModuleInfo moduleInfo = moduleInfoRepository.findById(moduleId).orElseThrow( () ->
         new ResourceNotFoundException(" ModuleInfo not found with moduleId :" + moduleId));

         
        if(!moduleInfo.getId().equals(moduleId)){
            throw new IllegalArgumentException("Task does not belong in this module  with moduleId :" + moduleId);
        }

         Task task = new Task();
         task.setModuleInfo(moduleInfo);
         applyTaskInfo(task, request);

         return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long taskId, UpdateTaskRequest request) {

        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task not found with taskId :" + taskId));

        
        if(!task.getModuleInfo().getId().equals(taskId)){
            throw new IllegalArgumentException("Task does not belong in this module  with taskId :" + taskId);
        }

        applyTaskInfo(task, request);

        return taskRepository.save(task);
        
    }

    @Override
    public void deleteTask(Long moduleId, Long taskId) {
        
        
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new ResourceNotFoundException("Task not found with taskId :" + taskId));

        if(!task.getModuleInfo().getId().equals(moduleId)){
            throw new IllegalArgumentException("Task does not belong in this module  with moduleId :" + moduleId);
        }

        taskRepository.delete(task);
    }

      private void applyModuleInfo(ModuleInfo module, AddModuleRequest request) {
                module.setTitle(request.getTitle());
                
                     module.setDescription(request.getDescription());

            }

        private void  applyModuleInfo(ModuleInfo module, UpdateModuleRequest request) {

                module.setTitle(request.getTitle());

                module.setDescription(request.getDescription());
            
                }

       private void   applyTaskInfo(Task task, AddTaskRequest request) {

                task.setType( request.getType());

                task.setDescription( request.getDescription());
            
        }

      private void applyTaskInfo (Task task, UpdateTaskRequest request) {

        task.setType(request.getType());
           task.setDescription(request.getDescription());
    
        }


  

}
