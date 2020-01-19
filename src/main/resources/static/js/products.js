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
				productsInCart.splice(i, 1);
				$("#product" + id).addClass("hidden");
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
		toastr.error('The cart is empty!', 'Can\'t proceed');
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

var actualPage = 1;
var itemsPerPage = 2;
var totalPages = 0;

function paginationFirst(){
	if(actualPage != 1){
		$("#nextPage").val(1);
		$("#paginationForm").submit();
	}
}

function paginationPrevious(){
	if(actualPage != 1){
		$("#nextPage").val(actualPage - 1);
		$("#paginationForm").submit();
	}
}

function paginationPage(index){
	var auxNextPage = $(".pagination .page-item.page a")[index].innerHTML
	
	if(parseInt(auxNextPage) != actualPage){
		$("#nextPage").val(parseInt(auxNextPage));
		$("#paginationForm").submit();
	}
}

function paginationNext(){
	console.log("paginationNext");	
	if(actualPage < totalPages){
		$("#nextPage").val(actualPage + 1);
		$("#paginationForm").submit();
	}
}

function paginationLast(){	
	if(actualPage < totalPages){
		$("#nextPage").val(totalPages);
		$("#paginationForm").submit();
	}
	
}

function renderPagination(){
	$(".pagination .page-item.page.active").removeClass("active");
	$(".pagination .page-item.disabled").removeClass("disabled");
	
	if(actualPage==1){
		$(".pagination .page-item.page a")[0].innerHTML = 1,
		$(".pagination .page-item.page a")[1].innerHTML = 2;
		$(".pagination .page-item.page a")[2].innerHTML = 3;
		$($(".pagination .page-item.page").get(0)).addClass("active");
		$(".pagination .page-item.previous").addClass("disabled");
		$(".pagination .page-item.first").addClass("disabled");
	}else if(actualPage == totalPages){
		$(".pagination .page-item.page a")[0].innerHTML = totalPages - 2,
		$(".pagination .page-item.page a")[1].innerHTML = totalPages - 1;
		$(".pagination .page-item.page a")[2].innerHTML = totalPages;
		$($(".pagination .page-item.page").get(2)).addClass("active");
		$(".pagination .page-item.next").addClass("disabled");
		$(".pagination .page-item.last").addClass("disabled");
	}else{
		$(".pagination .page-item.page a")[0].innerHTML = actualPage - 1,
		$(".pagination .page-item.page a")[1].innerHTML = actualPage;
		$(".pagination .page-item.page a")[2].innerHTML = actualPage + 1;
		$($(".pagination .page-item.page").get(1)).addClass("active");
	}	
	
	if(itemsPerPage > $("#totalProducts").val()){
		if(!$(".pagination").hasClass("hidden")){
			$(".pagination").addClass("hidden");
		}
	}else{
		if($(".pagination").hasClass("hidden")){
			$(".pagination").removeClass("hidden");
		}
	}
	
	if(itemsPerPage*2 >= parseInt($("#totalProducts").val())){
		if(!$($(".pagination .page-item.page a").get(2)).hasClass("hidden")){
			$($(".pagination .page-item.page a").get(2)).addClass("hidden");
		}
	}else{
		if($($(".pagination .page-item.page a").get(2)).hasClass("hidden")){
			$($(".pagination .page-item.page a").get(2)).removeClass("hidden");
		}
	}
}