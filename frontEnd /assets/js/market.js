$(document).ready(function () {
 

var total = 0;

    $("#order-btn-1").click(function (event) {
        total += 20;
        $('.counter').text(total);
        $('#cart-table').append('<tr><td>Shrimp</td><td>These are not from the river, son</td><td>20 $</td><td><button class="btn btn-danger" id="delete-btn">Remove</button></td></tr>');
      });

      $("#order-btn-2").click(function (event) {
        total += 20;
        $('.counter').text(total);
        $('#cart-table').append('<tr><td>Mariachi</td><td>Playing music all day long, baby!</td><td>20 $</td><td><button class="btn btn-danger" id="delete-btn">Remove</button></td></tr>');
      });

      $("#order-btn-3").click(function (event) {
        total += 20;
        $('.counter').text(total);
        $('#cart-table').append('<tr><td>Taco</td><td>Wrapped Paradise, specially for you</td><td>20 $</td><td><button class="btn btn-danger" id="delete-btn">Remove</button></td></tr>');
      });

      $("#reset-btn").click(function (event) {
        total = 0;
        $('.counter').text(total);
        $("#cart-table").find("tr:gt(0)").remove();
      });

      $("#cart-table").on("click", "#delete-btn", function() {
        total -= 20;
        $('.counter').text(total);
        $(this).closest("tr").remove();
     });
});
