package com.example.user.Controller;

import com.example.user.Model.User;
import com.example.user.Repository.UserRepo;
import com.example.user.VO.Department;
import com.example.user.VO.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo repo;

    @Autowired
    private RestTemplate restTemplate;
    @PostMapping("/save")
    public ResponseEntity<User> submitu(@RequestBody User user)
    {
        return ResponseEntity.ok().body(repo.save(user));
    }
    @GetMapping("/usr")
    public ResponseEntity<List<User>> getall()
    {
        return ResponseEntity.ok().body(repo.findAll());
    }
    @GetMapping("/{uid}")
    public ResponseEntity<ResponseTemplateVO> findUserWithDept(@PathVariable int uid)
    {
        ResponseTemplateVO op=new ResponseTemplateVO();
        User user=repo.findByUid(uid);
        if(user==null)
        {
            return ResponseEntity.notFound().build();
        }
        String url="http://localhost:8081/department/{did}";
        Department department=restTemplate.getForObject(url,Department.class,user.getDid());
        op.setUser(user);
        op.setDepartment(department);
        return ResponseEntity.ok().body(op);
    }
    @GetMapping("/ALL")
    public ResponseEntity<List<Department>>findAllD(){
        String url="http://localhost:8081/department/dept";
        List<Department> d=restTemplate.getForObject(url,List.class);
        return ResponseEntity.ok().body(d);
    }
    @PostMapping("/POST")
    public ResponseEntity<Department> sub(@RequestBody Department d1){
        String url="http://localhost:8081/department/submit";
        Department ds=restTemplate.postForObject(url,d1,Department.class);
        return ResponseEntity.ok().body(ds);

    }
    @PutMapping("/PUT/{id}")
    public ResponseEntity<Department>put(@RequestBody Department d2, @PathVariable int id){
        String url="http://localhost:8081/department/pp/{id}";
//        d2.setDid(id);
        restTemplate.put(url,d2,id,Department.class);
        return ResponseEntity.ok().body(d2);
    }
    @DeleteMapping("/DELETE/{id}")
    public ResponseEntity<String> remove(@PathVariable int id){
        String url="http://localhost:8081/department/dd/{id}";
        restTemplate.delete(url,id,String.class);
        return  ResponseEntity.ok().body("deleted") ;
    }
    @GetMapping("GBI/{did}")
    public ResponseEntity<Department> gbyi(@PathVariable int did){
        String url="http://localhost:8081/department/{did}";
        Department d=restTemplate.getForObject(url,Department.class,did);
        return ResponseEntity.ok().body(d);
    }

}
