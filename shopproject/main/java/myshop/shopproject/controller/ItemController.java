package myshop.shopproject.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myshop.shopproject.controller.form.ItemEditForm;
import myshop.shopproject.controller.form.ItemForm;
import myshop.shopproject.domain.item.Book;
import myshop.shopproject.domain.item.Item;
import myshop.shopproject.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RequestMapping("/items")
@RequiredArgsConstructor
@Controller
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/new")
    public String itemForm(Model model) {

        ItemForm itemForm = new ItemForm();
        System.out.println("itemForm.getPrice() = " + itemForm.getPrice());
        System.out.println("itemForm.getStockQuantity() = " + itemForm.getStockQuantity());
        model.addAttribute("form", itemForm);

        System.out.println("itemForm.getPrice() = " + itemForm.getPrice());
        System.out.println("itemForm.getStockQuantity() = " + itemForm.getStockQuantity());


        return "items/createItemForm";
    }

    @PostMapping("/new")
    public String itemSave(@Valid @ModelAttribute(name = "form") ItemForm form, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.info("Errors = {}", bindingResult);
            return "/items/createItemForm";
        }


        Item item = Item.createItem(form.getName(), form.getStockQuantity(), form.getPrice(), form.getAuthor(), form.getIsbn());
        itemService.saveItem(item);
        return "redirect:/";
    }

    @GetMapping()
    public String items(Model model) {

        List<Item> items = itemService.findItemAll();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    @GetMapping("/{itemId}/edit")
    public String itemEditForm(@PathVariable Long itemId, Model model) {

        Book item = (Book)itemService.findItem(itemId);

        ItemEditForm form = new ItemEditForm();
        form.setPrice(item.getPrice());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());
        form.setName(item.getName());
        form.setStockQuantity(item.getStockQuantity());
        form.setId(item.getId());

        model.addAttribute("form", form);

        return "items/updateItemForm";
    }


    @PostMapping("/{itemId}/edit")
    public String itemEdit(@PathVariable Long itemId, @Valid @ModelAttribute(name = "form") ItemEditForm form, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "items/updateItemForm";

        }

        Book item = (Book)itemService.findItem(itemId);

        item.setAuthor(form.getAuthor());
        item.setPrice(form.getPrice());
        item.setName(form.getName());
        item.setStockQuantity(form.getStockQuantity());
        item.setIsbn(form.getIsbn());

        itemService.updateItem(item);

        return "redirect:/items";
    }






}
