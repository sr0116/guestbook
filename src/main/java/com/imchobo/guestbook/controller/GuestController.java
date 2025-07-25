package com.imchobo.guestbook.controller;

import com.imchobo.guestbook.dto.GuestbookDTO;
import com.imchobo.guestbook.dto.PageRequestDTO;
import com.imchobo.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("guestbook")
@RequiredArgsConstructor
public class GuestController {
  private final  GuestbookService service;

  @GetMapping
  public String index(){
    return  "redirect:/guestbook/list";
  }
  @GetMapping("list")
  public void list(@ModelAttribute("requestDto") PageRequestDTO  dto, Model model){
   model.addAttribute("dto", service.getList(dto));
  }
  @GetMapping("register")
  public void register(GuestbookDTO dto,  Model model){
  }
  @PostMapping("register")
  public String register(GuestbookDTO dto, RedirectAttributes rttr){
    rttr.addFlashAttribute("msg", service.write(dto));
    return "redirect:/guestbook/list";
  }

  @GetMapping("read")
  public void read(@ModelAttribute("pageDto") PageRequestDTO dto, Model model, Long gno){
    model.addAttribute("dto", service.read(gno));
//    model.addAttribute("pageDto", dto);
  }
  @GetMapping("modify")
  public void modify(@ModelAttribute("pageDto") PageRequestDTO dto, Model model, Long gno){
    model.addAttribute("dto", service.read(gno));
//    model.addAttribute("pageDto", dto);
  }
  @PostMapping("modify")
  public String modify( PageRequestDTO dto, GuestbookDTO guestbookDTO, Long gno, RedirectAttributes rttr){
  service.modify(guestbookDTO);
  rttr.addAttribute("gno", guestbookDTO.getGno());
  rttr.addAttribute("page", dto.getPage());
  rttr.addAttribute("size", dto.getSize());
   return "redirect:/guestbook/read";
//    model.addAttribute("pageDto", dto);
  }
  @PostMapping("remove")
  public String remove( PageRequestDTO dto, GuestbookDTO guestbookDTO, Model model, Long gno, RedirectAttributes rttr){
    service.remove(gno);

//    model.addAttribute("pageDto", dto);
    rttr.addAttribute("gno", guestbookDTO.getGno());
    rttr.addAttribute("page", dto.getPage());
    rttr.addAttribute("size", dto.getSize());
    return "redirect:/guestbook/list";
  }

}
