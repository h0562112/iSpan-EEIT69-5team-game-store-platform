package com.ispan.eeit69.controller.membercontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ispan.eeit69.model.Friends;
import com.ispan.eeit69.repository.memberRepository.FriendRepository;
import com.ispan.eeit69.service.member.FriendService;
import com.ispan.eeit69.service.member.memberService;
import com.ispan.eeit69.utils.memberutils.Msg;
import com.ispan.eeit69.utils.memberutils.MyWebSocket;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hyb
 * @since 2022-06-16 15:34:50
 */
@CrossOrigin(origins = "http://10.0.101.72:8080")
@RestController
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	private FriendRepository friendRepository;
	private FriendService messageService;
	memberService  employeeService;
    @Autowired
    MyWebSocket webSocket;

    @CrossOrigin(origins = "http://10.0.101.72:8080")
    @PostMapping("/save")
    public Msg addFriend(@RequestBody Friends friend) {
        // 使用 save 方法保存 Friend 对象	
	
        Friends savedFriend = friendRepository.save(friend);
       
        // 使用方法連鎖，設置成功、代碼、消息和Friend數據
        return Msg.success()
                  .setCode(200)
                  .setMessage("自定義成功消息")
                  .addData("friend", savedFriend);
    }
    @CrossOrigin(origins = "http://10.0.101.72:8080")
    @GetMapping("/get/{id}")
    public Msg get(@PathVariable("id") int id) {
        List<Friends> list = friendRepository.findByUserID1(id);
//        List<Object[]> friendsList = employeeService.findByfriendsid(id);
        // 使用 Msg.success() 創建成功的 Msg 實例，然後添加數據
        return Msg.success()
                .data("friends", list);
//                .data("friendlist", friendsList);
        		  
    }

    @GetMapping("/del/{user1}/{user2}")
    public Msg del(@PathVariable("user1") int user1, @PathVariable("user2") int user2) {
        friendRepository.deleteByUserID1AndUserID2(user1, user2);

        // 返回成功的 Msg 对象
        return Msg.success();

    }

}

