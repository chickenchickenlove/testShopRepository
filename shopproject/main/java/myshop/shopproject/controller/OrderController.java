package myshop.shopproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myshop.shopproject.controller.form.OrderForm;
import myshop.shopproject.domain.Member;
import myshop.shopproject.domain.Order;
import myshop.shopproject.domain.item.Item;
import myshop.shopproject.repository.OrderSearch;
import myshop.shopproject.service.ItemService;
import myshop.shopproject.service.MemberService;
import myshop.shopproject.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;
    private final MemberService memberService;


    @GetMapping("/order")
    public String orderForm(Model model) {

        List<Member> members = memberService.findMemberAll();
        List<Item> items = itemService.findItemAll();


        OrderForm orderForm = new OrderForm();


        model.addAttribute("orderForm", orderForm);
        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }


    @PostMapping("/order")
    public String order(@RequestParam(name = "memberId") Long memberId,
                        @RequestParam(name = "itemId") Long itemId,
                        @Valid @ModelAttribute(name = "orderForm") OrderForm orderForm,
                        BindingResult bindingResult,
                        Model model) {

        Integer count = orderForm.getCount();
        log.info("BindingReulst = {}", bindingResult);

        if (bindingResult.hasErrors()) {
            log.info("herer");
            return "order/orderForm";
        }



        orderService.saveOrder(memberId, itemId, count);
        return "redirect:/";

    }


    @GetMapping("/orders")
    public String orderList(@ModelAttribute(name = "orderSearch") OrderSearch orderSearch, Model model) {

        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }



    @PostMapping("/orders/{orderId}/cancel")
    public String orderCancel(@PathVariable(name = "orderId") Long orderId) {
        orderService.orderCancel(orderId);
        return "redirect:/";
    }







}









