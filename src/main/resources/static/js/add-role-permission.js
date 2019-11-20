var permissionSet = new Set();
var listCheck = new Set();

$('.add-role-permission-checkbox:checked').each(function () {
    listCheck.add(parseInt($(this).val()));
});

permissionSet = listCheck;
console.log('permissionset');
console.log(permissionSet);

$(document).ready(function(data) {
    $('.add-role-permission-checkbox').click(function(){
        var permissionId = parseInt($(this).attr('value'));
        if (permissionSet.has(permissionId)) {
            permissionSet.delete(permissionId);
        }
        else {
            permissionSet.add(permissionId);
        }

        console.log(permissionSet);
    });
});

$("#add-role-permission").click(function () {
    var roleId = parseInt($("#current-role-id").attr("value"));

    fetch("/add-role-permission", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            roleId: roleId,
            permissionIds: Array.from(permissionSet)
        })
    }).then(function () {
        location.reload();
    });
});
