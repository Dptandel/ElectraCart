package com.app.electracart.Helper;

import android.content.Context;
import android.widget.Toast;

import com.app.electracart.Domain.PopularDomain;

import java.util.ArrayList;

public class ManagementCart {

    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(PopularDomain item) {
        ArrayList<PopularDomain> cartList = getListCart();

        // Check if item is already in the cart
        boolean exists = false;
        for (PopularDomain existingItem : cartList) {
            if (existingItem.getTitle().equals(item.getTitle())) {
                existingItem.setNumberInCart(existingItem.getNumberInCart() + item.getNumberInCart());
                exists = true;
                break;
            }
        }

        // If item doesn't exist, add it
        if (!exists) {
            cartList.add(item);
        }

        // Save the updated list back to TinyDB
        tinyDB.putListObject("CartList", cartList);
    }


    public ArrayList<PopularDomain> getListCart() {
        try {
            ArrayList<PopularDomain> cartList = tinyDB.getListObject("CartList", PopularDomain.class);
            if (cartList == null) {
                return new ArrayList<>(); // Return empty list if null
            }
            return cartList;
        } catch (Exception e) {
            // Handle deserialization error
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list on error
        }
    }


    public void minusNumberItem(ArrayList<PopularDomain> listItem, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listItem.get(position).getNumberInCart() == 1) {
            listItem.remove(position);
        } else {
            listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList", listItem);
        changeNumberItemsListener.change();
    }

    public void plusNumberItem(ArrayList<PopularDomain> listItem, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList", listItem);
        changeNumberItemsListener.change();
    }

    public Double getTotalFee() {
        ArrayList<PopularDomain> listItem = getListCart();
        double fee = 0;
        for (PopularDomain item : listItem) {
            fee += item.getPrice() * item.getNumberInCart();
        }
        return fee;
    }
}
