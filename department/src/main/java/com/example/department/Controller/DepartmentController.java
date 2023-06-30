package com.example.department.Controller;

import com.example.department.Entity.Department;
import com.example.department.Repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepo repo;

    @PostMapping("/submit")
    public ResponseEntity<Department>submit(@RequestBody Department department){
        return ResponseEntity.ok().body(repo.save(department));
    }
    @GetMapping("/dept")
    public ResponseEntity<List<Department>>getalldept()
    {

        return ResponseEntity.ok().body(repo.findAll());
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Department>> getbyid(@PathVariable int id)
//    {
//
//        return ResponseEntity.ok().body(repo.findById(id));
//
//    }
//
    @GetMapping("/{did}")
    public ResponseEntity<Department>getbyid(@PathVariable int did)
    {
        Department department=repo.findByDid(did);
        if(department==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(department);

    }
    @PutMapping("/pp/{id}")
    public ResponseEntity<Department>putobj(@RequestBody Department dp,@PathVariable int id){
        Optional<Department>dpt= repo.findById(id);
        if(dpt.isPresent()){
            Department dep=dpt.get();
            if(dp.getDcode()!=null)
            dep.setDcode(dp.getDcode());
            if(dp.getDname()!=null)
            dep.setDname(dp.getDname());
            return ResponseEntity.ok().body(repo.save(dep));
        }
        return null;

    }
    @DeleteMapping("/dd/{id}")
    public ResponseEntity<String >delete(@PathVariable int id){
       repo.deleteById(id);
       return ResponseEntity.ok().body("dele");
    }
}
