var roleSet = new Set();
var listCheck = new Set();

$('.add-user-role-checkbox:checked').each(function () {
    listCheck.add(parseInt($(this).val()));
});

roleSet = listCheck;
console.log('roleset');
console.log(roleSet);

$(document).ready(function(data) {
    $('.add-user-role-checkbox').click(function(){
        var roleId = parseInt($(this).attr('value'));
        if (roleSet.has(roleId)) {
            roleSet.delete(roleId);
        }
        else {
            roleSet.add(roleId);
        }

        console.log(roleSet);
    });
});

$("#add-user-role").click(function () {
    var userId = parseInt($("#current-user-id").attr("value"));

    fetch("/add-user-role", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            userId: userId,
            roleIds: Array.from(roleSet)
        })
    }).then(function () {
        location.reload();
    });
});