function send() {
	let email = $("#email").val();
	console.log("Amit");
	$("#result").html("");
	$.ajax({
		url : "/client/sendotp?email=" + email,
		success : function(result) {
			$("#result").html("Otp Sended Successfully");
			otp = result;
		},
		error : function(result) {
			$("#result").html("Something Went Wrong while sending otp");
		}
	});
}

function check() {
	let enteredotp = $("#otpInput").val();
	$.ajax({
		url : "/client/checkotp?otp=" + enteredotp,
		success : function(result) {
			if (result == "success") {
				$("#form").submit();
				return;
			}
			alert("Invalid otp");
			$("#otpInput").val("");
		},
		error : function(result) {
			$("#result").html("Something Went Wrong");
		}
	});
}

// login function
function login() {
	let email = $("#email").val();
	let password = $("#password").val();
	$.ajax({
		url : "../login?email=" + email + "&password=" + password,
		success : function() {
			$("#result").html("Otp Sended Successfully");
		},
		error : function(result) {
			$("#result").html("Invalid credentials");
		}
	});
}
function submitForm() {
	$("#clientSignUp").submit();
}

// search function
function mySearch() {
	var name = $("#search").val();
	console.log(name);
	if (name != "") {
		$.ajax({
			url : "get?restro=" + name,
			method : "get",
			success : function(result) {
				$('tr').remove(".rex");
				$("#box").show();
				for (i = 0; i < result.length; i++) {
					console.log(result[i]);
					$('#searchbox').append(
							'<tr class="rex"><td><a href="getRestro?id='
									+ result[i].id + '" class="btn">'
									+ result[i].restro + '</td></tr>');
				}
			}
		});
	} else {
		$("#box").hide();

	}
}

// add quantity in cart
function add(quantity) {

	quan = $("#" + quantity.id + "").val();
	$("#" + quantity.id + "").val(parseInt(quan) + 1);

}

// substract qyantity in cart
function sub(quantity) {
	quan = $("#" + quantity.id + "").val();
	if (quan != 0)
		$("#" + quantity.id + "").val(parseInt(quan) - 1);

}

// add to cart function
function addToCart(id) {
	if ($("#id" + id).val() != "0") {
		$.ajax({
			url : "addtocart?menuId=" + $("#" + id).val() + "&quantity="
					+ $("#id" + id).val(),
			method : "POST",
			success : function(result) {
				alert("Added to cart Successfully");
			},
			error : function(result) {
				console.log(result.status)
				console.log(result + "nhi Aaya");
			}
		});
	} else {
		alert("Select Quantity First");
	}
}

// delete from cart
function deleteFromCart(id) {
	$.ajax({
		url : "deletefromcart?cart=" + id,
		method : "POST",
		success : function(result) {
			alert("deleted to cart Successfully");
			$("#amount").html(result);
			$('tr').remove("#tr" + id);
		},
		error : function(result) {
			console.log(result.status)
			console.log(result + "nhi Aaya");
		}
	});
}

// payment function
function pay() {
	let amount = $("#amount").html();
	if (amount == '' || amount == null || amount == 0.0) {
		alert("Add Something to buy");
		return;
	}
	$.ajax({
		url : "/user/payment",
		method : "POST",
		data : JSON.stringify({
			"amount" : amount
		}),
		contentType : 'application/json',
		dataType : 'json',
		success : function(result) {
			console.log(result)
			console.log("going inside")
			var options = {
				"key" : "rzp_test_sgXOFdvGGQTLKV", // Enter the Key ID
				// generated from the
				// Dashboard
				"amount" : result.amount, // Amount is in currency subunits.
				// Default currency is INR. Hence,
				// 50000 refers to 50000 paise
				"currency" : "INR",
				"name" : "Online food Chaat",
				"description" : "Test Transaction",
				"order_id" : result.id, // This is a sample Order ID. Pass the
				// `id` obtained in the response of Step
				// 1
				"handler" : function(response) {
					console.log(response.razorpay_payment_id);
					console.log(response.razorpay_order_id);
					console.log(response.razorpay_signature)
					$.ajax({
						url : "/user/addtoorder",
						method : "POST",
						data : JSON.stringify({
							"amount" : amount
						}),
						contentType : 'application/json',
						success : function(result) {
							alert("Order Placed Successfully");
							$("#amount").html('0.0');
							$('tr').remove(".rem");
						},
						error : function(result) {
							console.log(result.status)
							console.log(result + "nhi Aaya");
						}
					});
				},
				"prefill" : {
					"name" : "",
					"email" : "",
					"contact" : ""
				},
				"notes" : {
					"address" : "Online Food Chat"
				},
				"theme" : {
					"color" : "#495848"
				}
			};
			var rzp1 = new Razorpay(options);
			rzp1.on('payment.failed', function(response) {
				alert(response.error.code);
				alert(response.error.description);
				alert(response.error.source);
				alert(response.error.step);
				alert(response.error.reason);
				alert(response.error.metadata.order_id);
				alert(response.error.metadata.payment_id);
			});
			rzp1.open();
		},
		error : function(result) {
			console.log(result)
			alert("Something went Wrong");
		}
	});

}

function cancelOrder(id) {
	$.ajax({
		url : "/user/cancelorder",
		method : "POST",
		data : JSON.stringify({
			"id" : id
		}),
		contentType : 'application/json',
		success : function(result) {
			if (result == "working") {
				alert("Order Canceled Successfully");
				$("#" + id).html("Cancelled");
				return;
			}
			alert("Something went Wrong");
		},
		error : function(result) {
			console.log(result.status)
			console.log(result + "nhi Aaya");
		}
	});
}
