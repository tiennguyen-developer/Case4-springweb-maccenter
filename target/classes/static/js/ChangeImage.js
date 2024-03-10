// JScript File
function mouse_over(ob)
    {
        
        document.getElementById(ob).style.borderColor = "#000000";
        
        document.getElementById("imgHinhAnh").src = document.getElementById(ob).src;   
    }
function mouse_out(ob)
    {
        document.getElementById(ob).style.borderColor = "#8D8D96";
    }

