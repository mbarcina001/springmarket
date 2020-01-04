var productsInCart = JSON.parse(localStorage.getItem("productsInCart")) || [];

class CartProduct {
  constructor(id, price, quantity) {
    this.id = id;
    this.price = price;
    this.quantity = quantity;
  }
}

function loadProductsFromCart(){
	for(var i=0; i<productsInCart.length; i++){
		if(!$("#product"+productsInCart[i].id+" .add-to-cart").hasClass("hidden")){
			$("#product"+productsInCart[i].id+" .add-to-cart").addClass("hidden")
		}
		
		if($("#product"+productsInCart[i].id+" .quantity").hasClass("hidden")){
			$("#product"+productsInCart[i].id+" .quantity").removeClass("hidden")
			$("#product"+productsInCart[i].id+" .quantity input").val(productsInCart[i].quantity)
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

function modifyProductList(quantity, id, price){
	
	var found = false;
	
	for(var i=0; i<productsInCart.length; i++){
		if(productsInCart[i].id === id){
			found = true
			
			if(quantity>0){
				productsInCart[i].quantity = quantity; 
				productsInCart[i].price = price;
			}else{
				productsInCart.splice(i, 1)
				break;
			}
		}
	}
	
	if(!found){
		productsInCart.push(new CartProduct(id, price, quantity));
	}
	
	localStorage.setItem("productsInCart", JSON.stringify(productsInCart));
}

function createDelivery(urlSuccess){
	if(productsInCart.length==0){
		toastr.error('The cart is empty!', 'Can\'t proceed')
	}else{
		$.ajax({ 
			url: '/delivery/createDelivery',    
		    type:"POST", 
		    contentType: "application/json; charset=utf-8",
		    data: JSON.stringify(productsInCart), //Stringified Json Object
		    async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
		    cache: false,    //This will force requested pages not to be cached by the browser          
		    processData:false, //To avoid making query String instead of JSON
		    success: function(){
		        window.location.href = urlSuccess;
	    	}
		});
	}
}