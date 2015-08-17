$(function() {
	$("#productsAutocomplete").autocomplete({
		minLength: 3,
		select: function(event, ui) {
			document.getElementById("productId").value = ui.item.id;
		},
		source: function(request, response) {
			$.get(window.location.origin + '/products' + '?type=' + request.term,
				function(products) {
					response(jQuery.map(products, function(product,
							indx) {
						var label = ' Brand: '
								+ product.brand
								+ ' Type: '
								+ product.type
								+ ' Color: '
								+ product.color
								+ '  Size: '
								+ product.size
								+ (product.gender == 'M' ? ' Mens'
										: ' Womens');
						return {
							id : product.id,
							value : label,
							label : label
						};
					}));
				});
		}
	});

	// customer
	$("#customerAutocomplete").autocomplete({
		minLength: 3,
		select: function(event, ui) {
			document.getElementById("customerId").value = ui.item.id;
		},
		source: function(request, response) {
			$.get(window.location.origin + '/customers' + '?email=' + request.term,
					function(customers) {
						response(jQuery.map(customers, function(
								customer, indx) {
							var label = customer.name + ' '
									+ customer.emailAddress;
							return {
								id : customer.id,
								value : label,
								label : label
							};
						}));
					});
		}
	});

	// customer search (in menu bar)
	$("#customerSearchAutocomplete").autocomplete({
		minLength: 3,
		select: function(event, ui) {
			document.getElementById("searchCustomerId").value = ui.item.id;
		},
		source: function(request, response) {
			$.get(window.location.origin + '/customers' + '?email='
					+ request.term, function(customers) {
				response(jQuery.map(customers, function(
						customer, indx) {
					var label = customer.emailAddress;
					return {
						id : customer.id,
						value : label,
						label : label
					};
				}));
			});
		}
	});
});