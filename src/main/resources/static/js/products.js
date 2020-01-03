function loadProductsFromCart(){
	for(var i=0; i<productsInCart.length; i++){
		if(!$("#product"+productsInCart[i].id+" .add-to-cart").hasClass("hidden")){
			$("#product"+productsInCart[i].id+" .add-to-cart").addClass("hidden")
		}
		
		if($("#product"+productsInCart[i].id+" .quantity").hasClass("hidden")){
			$("#product"+productsInCart[i].id+" .quantity").removeClass("hidden")
		}
		
		modifyProductList(productsInCart[i].quantity, productsInCart[i].id, productsInCart[i].price)
	}
}

function addToCart(element, id, price){
	$(element).addClass("hidden");
	$(element).parent().children(".quantity").removeClass("hidden");
	$(element).parent().children(".quantity").children("input").val(1);
	modifyProductList(1, id, price);
}

function removeElement(element, id, price){
	var new_quantity = parseInt($(element).parent().parent().children("input").val(), 10) - 1
	$(element).parent().parent().children("input").val(new_quantity);
	
	if(new_quantity==0){
		if($(element).parent().parent().parent().children(".add-to-cart").hasClass("hidden")){
			$(element).parent().parent().parent().children(".add-to-cart").removeClass("hidden");
		}
		
		if(!$(element).parent().parent().parent().children(".quantity").hasClass("hidden")){
			$(element).parent().parent().parent().children(".quantity").addClass("hidden");
		}
	}
	
	modifyProductList(new_quantity, id, price);
}

function addElement(element, id, price){
	var new_quantity = parseInt($(element).parent().parent().children("input").val(), 10) + 1
	$(element).parent().parent().children("input").val(new_quantity);
	
	modifyProductList(new_quantity, id, price)
}

function onInputChange(element, id, price){
	var new_quantity = parseInt($(element).val(), 10);

	if(new_quantity==0){
		if($(element).parent().parent().children(".add-to-cart").hasClass("hidden")){
			$(element).parent().parent().children(".add-to-cart").removeClass("hidden");
		}
		
		if(!$(element).parent().parent().children(".quantity").hasClass("hidden")){
			$(element).parent().parent().children(".quantity").addClass("hidden");
		}
	}
	
	modifyProductList(new_quantity, id, price)
}	