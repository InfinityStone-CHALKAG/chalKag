package infinitystone.chalKag.controller.chat;

import infinitystone.chalKag.biz.chat.ChatRoomDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Log4j2
public class RoomController {

  @Autowired
  private ChatRoomDAO chatRoomDAO;

  //채팅방 목록 조회
  @RequestMapping(value = "/rooms", method = RequestMethod.GET)
  public String rooms(Model model) {

    System.out.println("RoomController(rooms GET) In로그");

    model.addAttribute("list", chatRoomDAO.findAllRooms());

    System.out.println("RoomController(rooms GET) Out로그" + model.getAttribute("list"));

    return "chat/rooms";

  }

  // 채팅방 개설
  @RequestMapping(value = "/room", method = RequestMethod.POST)
  public String createRoom(@RequestParam String name, RedirectAttributes rttr) {

    System.out.println("RoomController(room POST) In로그");

    rttr.addFlashAttribute("roomName", chatRoomDAO.createChatRoomDTO(name));

    System.out.println("RoomController(room POST) Out로그");

    return "redirect:/rooms";
  }

  //채팅방 조회
  @GetMapping("/room")
  public void getRoom(String roomId, Model model) {

    System.out.println("RoomController(room GET) In로그");

    model.addAttribute("room", chatRoomDAO.findRoomById(roomId));

    System.out.println("RoomController(room GET) Out로그" + model.getAttribute("room"));
  }

}
