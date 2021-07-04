/**
 * 
 */

$(document).ready(function() {
	$('.delete-item').on('click', function (){
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'remove';
	    /*]]>*/
		
		var id=$(this).attr('id');
		
		bootbox.confirm({
			message: "Are you sure to remove this item? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.post(path, {'id':id}, function(res) {
						location.reload();
					});
				}
			}
		});
	});
	
	
	
//	$('.checkboxItem').click(function () {
//        var id = $(this).attr('id');
//        if(this.checked){
//            itemIdList.push(id);
//        }
//        else {
//            itemIdList.splice(itemIdList.indexOf(id), 1);
//        }
//    })
	
	$('#deleteSelected').click(function() {
		var idList= $('.checkboxItem');
		var itemIdList=[];
		for (var i = 0; i < idList.length; i++) {
			if(idList[i].checked==true) {
			itemIdList.push(idList[i]['id'])
			}
		}
		
		console.log(itemIdList);
		
		/*<![CDATA[*/
	    var path = /*[[@{/}]]*/'removeList';
	    /*]]>*/
	    
	    bootbox.confirm({
			message: "Are you sure to remove all selected items? It can't be undone.",
			buttons: {
				cancel: {
					label:'<i class="fa fa-times"></i> Cancel'
				},
				confirm: {
					label:'<i class="fa fa-check"></i> Confirm'
				}
			},
			callback: function(confirmed) {
				if(confirmed) {
					$.ajax({
						type: 'POST',
						url: path,
						data: JSON.stringify(itemIdList),
						contentType: "application/json",
						success: function(res) {
							console.log(res); 
							location.reload()
							},
						error: function(res){
							console.log(res); 
							location.reload();
							}
					});
				}
			}
		});
	});
	
	$("#selectAllItems").click(function() {
		if($(this).prop("checked")==true) {
			$(".checkboxItem").prop("checked",true);
		} else if ($(this).prop("checked")==false) {
			$(".checkboxItems").prop("checked",false);
		}
	})
});


/**
 * 
 */

 function checkBillingAddress() {
	if($("#theSameAsShippingAddress").is(":checked")) {
		$(".billingAddress").prop("disabled", true);
	} else {
		$(".billingAddress").prop("disabled", false);
	}
}

function checkPasswordMatch() {
	var password = $("#txtNewPassword").val();
	var confirmPassword = $("#txtConfirmPassword").val();
	
	if(password == "" && confirmPassword =="") {
		$("#checkPasswordMatch").html("");
		$("#updateUserInfoButton").prop('disabled', false);
	} else {
		if(password != confirmPassword) {
			$("#checkPasswordMatch").html("Passwords do not match!");
			$("#updateUserInfoButton").prop('disabled', true);
		} else {
			$("#checkPasswordMatch").html("Passwords match");
			$("#updateUserInfoButton").prop('disabled', false);
		}
	}
}

$(document).ready(function(){
	$(".cartItemQty").on('change', function(){
		var id=this.id;
		
		$('#update-item-'+id).css('display', 'inline-block');
	});
	$("#theSameAsShippingAddress").on('click', checkBillingAddress);
	$("#txtConfirmPassword").keyup(checkPasswordMatch);
	$("#txtNewPassword").keyup(checkPasswordMatch);
});