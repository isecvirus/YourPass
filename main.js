// this is the main javascript file for the pgfpi tool.
// Needed libraries:
// npm install blueimp-md5

function InputBoxHitEnter(){
    var input_box = document.getElementById('info-input'); // get inserted info from input box.
    var add_button = document.getElementById('add-button'); // get (Add) button id to click when interacting.

    input_box.addEventListener('keyup', function(event){ // add listener to the input box.
        if (event.keyCode == 13){ // add the (return || enter) button code which is 13.
            event.preventDefault(); // break last enter interaction and do the current..
            // (happens when click return button more than one time in the same time).

            add_button.click(); // click the add button when clicking on return..
            // (in the same time the add button is working fine).
        }
    })
}

var array_data = []; // make array for table data.
function AddInfo() { // make a function for adding new info to the table.
    var input_box = document.getElementsByClassName('info-input'); // get input box class name.
    var input_box_value = input_box[0].value; // get inserted info from input box by the class name from the above vaiable.

    var table = document.getElementById("info-table"); // get table id.
    var table_len = table.rows.length; // get length of the table.

    if (
        !(array_data.includes(input_box_value))
        && !(array_data.includes(input_box_value.replace(/ /g, '')))
        && !(input_box_value === '')
        && !(input_box_value.replace(/ /g, '').length === 0)
        ) {
        // add inserted info if not in table && not in (array data) |or| not input box is empty..
        // else ignore if the inserted info is already exist |or| the info box is empty
        array_data.push(input_box_value); // insert the new info into the data array.
        var row = table.insertRow(); // get last row to add new info.
        var index = row.insertCell(0); // find the correct new info index.
        var info = row.insertCell(1); // get the info from the info box.
        var actionBtns = row.insertCell(2); // make action buttons (copy edit delete).
        index.innerHTML = table_len + 1; // set the correct new info index.
        info.innerHTML = input_box_value.replace(/ /g, ''); // set the info from the info box.
        actionBtns.innerHTML = `
        <span title='Copy information' onclick='Copy(closest("tr"))' class='action-btns'>Copy</span>
        <span title='Delete information' onclick='Delete(closest("tr"))' class='action-btns'>Delete</span>
        `; // set action buttons (copy edit delete).
        // <span title='Edit information' onclick='Edit(closest("tr"), closest("span"))' id='Edit-btn' class='Edit-btn'>Edit</span>
        input_box[0].value = '' // clear input box.
    }
}

// function Load(file){
//     var ReadFile = new FileReader();
//     // var selected = document.getElementById('load-button');
//     var filename = file.files[0].name; // get only the file name.
//     var fullpath = file.files[0].path; // get full path of the selected file.

//     document.getElementById('file-name').innerText = filename;
//     
// }

// -------------------------------------
// -------- Start copy function --------
// -------------------------------------
function Copy(row) {
    var info = row.cells[1].innerText;
    navigator.clipboard.writeText(info);
}
// -------------------------------------
// --------- End copy function ---------
// -------------------------------------

// -------------------------------------
// -------- Start edit function --------
// -------------------------------------
function Edit(info, btn) {
    var clicked = info.cells;
    old_value = clicked[1].innerHTML;
    
    function update(cell, value){
        if (cell === '#'){
            clicked[0].innerHTML = value;
        }
        else if (cell === 'info'){
            clicked[1].innerHTML = value;
        }
        else if (cell === 'action'){
            clicked[2].innerHTML = value;
        }
        else {
            return;
        }
    }

    function data(cell){
        if (cell === '#'){
            return clicked[0].innerText;
        }
        else if (cell === 'info'){
            return clicked[1].innerText;
        }
        else if (cell === 'action'){
            return clicked[2].innerText;
        }
        else {
            return;
        }
    }
    
    update('info', `<input class="new-info" value="${data('info')}" type="text">`)
    var get_index = info.cells[0].innerText - 1;

    btn.innerHTML = 'Save';
    btn.style.color = 'green';
    btn.title = 'Save information';

    function FromSave2Edit() {
        btn.addEventListener('click', function() {
            try {
                var new_value = document.getElementsByClassName('new-info')[get_index].value;
                old_value = new_value;
                if (!(array_data.includes(new_value)) || new_value === old_value){
                    btn.innerHTML = "<span title='Edit information' onclick='Edit(closest('tr'), closest('span'))' id='Edit-btn' class='Edit-btn'>Edit</span>";
                    btn.style.color = 'red';
                    btn.title = 'Edit information';

                    info.cells[1].innerHTML = '<td>' + new_value + '</td>';
                    
                    array_data.splice(get_index, 1, new_value);
                    
                }
            }
            catch {
                var new_value = old_value;
                old_value = new_value;
                
                update('info', `<input class="new-info" value="${old_value}" type="text">`)
                if (!(array_data.includes(new_value)) || new_value === old_value){
                    
                    array_data.splice(get_index, 1, new_value);
                    

                    btn.innerHTML = 'Save';
                    btn.style.color = 'green';
                    btn.title = 'Save information';
                }
            }
        })}
    btn.onclick = FromSave2Edit()
}
// -------------------------------------
// --------- end edit function ---------
// -------------------------------------

function Delete(row){
    var table = document.getElementById("info-table");
    var row_info = row.cells[1].innerText;
    var row_index = array_data.indexOf(row_info);

    array_data.splice(row_index, 1); //delete clicked row from the <array_data> variable.
    row.remove(); //delete row from the table.

    // delete all to fix index
    var rowLength = table.rows.length;
    for (i=0;i<rowLength;i++) {
        table.deleteRow(0);
    }

    // restor all - except the deleted one
    for (n=0;n<array_data.length;n++) {
        var row = table.insertRow();
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        cell1.innerHTML = n + 1;
        cell2.innerHTML = array_data[n];
        cell3.innerHTML = `
        <span onclick='Copy(closest("tr"))' class='action-btns'>Copy</span>
        <span onclick='Delete(closest("tr"))' class='action-btns'>Delete</span>
        `; // <span onclick='Edit(closest("tr"), closest("span"))' id='Edit-btn' class='Edit-btn'>Edit</span>
    }
}

// Start real work (mixing victim information to get passwords list).
function PasswordStrength(password) {
    var strength = 0;
    var upper = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    var lower = 'abcdefghijklmnopqrstuvwxyz';
    var numbers = '0123456789';
    var puncs = '~`!@#$%^&*()_+=-{[]}\\|"\';:/?.>,<';

    for (i=0;i<password.length;i++){
        if (upper.includes(password[i])){
            strength += 2
        }
        else if (lower.includes(password[i])){
            strength += 1
        }
        else if (numbers.includes(password[i])){
            strength += 3
        }
        else if (puncs.includes(password[i])){
            strength += 4
        }
        else {strength += 1}
    }

    if (password.length >= 1 && password.length <= 4){
        strength += 1
    }
    else if (password.length >= 5 && password.length <= 7){
        strength += 2
    }
    else if (password.length >= 8 && password.length <= 10){
        strength += 3
    }
    else if (password.length >= 10 && password.length <= 15){
        strength += 4
    }
    else if (password.length >= 16 && password.length <= 21){
        strength += 5
    }
    else if (password.length >= 27 && password.length <= 32){
        strength += 10
    }
    else if (password.length >= 33 && password.length <= 40){
        strength += 15
    }
    else if (password.length >= 41 && password.length <= 50){
        strength += 25
    }
    else {
        strength += 50
    }


    // console.log(password.match(/[a-z]+/)[0]);
    // if (password.match(/[a-z]+/)) {
    //   strength += password.match(/[a-z]+/) + 2;
    // }

    // console.log(password.match(/[A-Z]+/)[0]);
    // if (password.match(/[A-Z]+/)) {
    //   strength += password.match(/[A-Z]+/) + 2;
    // }

    // console.log(password.match(/[0-9]+/)[0]);
    // if (password.match(/[0-9]+/)) {
    //   strength += password.match(/[0-9]+/) + 1;
    // }

    // console.log(password.match(/[.,\\/#!$%^&*;:{}=\-_`~()]+/)[0]);
    // if (password.match(/[.,\/#!$%\^&\*;:{}=\-_`~()]+/)) {
    //   strength += password.match(/[~`!@#$%^&*()_+=-}]{[|\\"':;?/>.<,]+/) + 3;
  
    // }

    if (strength > 100){
        return '100'
    }
    else {
        return strength
    }

    // switch (strength) {
    //   case 0:
    //     return '0';
    //     break;
  
    //   case 1:
    //     return '25';
    //     break;
  
    //   case 2:
    //     return '50';
    //     break;
  
    //   case 3:
    //     return '75';
    //     break;
  
    //   case 4:
    //     return '100';
    //     break;
    // }
}
  
function Generate(){
    var table = document.getElementById("pass-table"); // get table id.
    var rowLength = table.rows.length; // delete all to start new generating process
    var information_length = array_data.length;
    function NewPassword(password, level){
        var table_len = table.rows.length; // get length of the table.
        var row = table.insertRow(); // get last row index to add new password.
    
        var indexcell = row.insertCell(0); // find the correct new password index.
        var passwordcell = row.insertCell(1); // get the correct cell for the password.
        var levelcell = row.insertCell(2); // what level does this password found at?.
        var password_lengthcell = row.insertCell(3); // password length.
        var password_strengthcell = row.insertCell(4); // password strength.
        indexcell.innerHTML = table_len + 1; // set the correct new password index.
        passwordcell.innerHTML = password.replace(/ /g, ''); // set the password from the function parameter (password).
        levelcell.innerHTML = level; // set the level from the function parameter (level).
        password_lengthcell.innerHTML = password.length; // set the password length.

        function CheckPasswordStrength(){
            var percentage = PasswordStrength(password);
            password_strengthcell.innerHTML = `<td>${percentage}%</td>`;
            if (percentage >= 0 && percentage <= 25){
                password_strengthcell.style.color = '#fc3d03' // red #fc3d03
            }
            else if (percentage >= 26 && percentage <= 50){
                password_strengthcell.style.color = '#fc0303' // orange #fc0303
            }
            else if (percentage >= 51 && percentage <= 75){
                password_strengthcell.style.color = '#fca103' // yellow #fca103
            }
            else if (percentage >= 76 && percentage <= 100){
                password_strengthcell.style.color = '#5afc03' // green #5afc03
            }
            else {
                password_strengthcell.style.color = '#5c5c5c' // grey #5c5c5c
            }
        }
        CheckPasswordStrength();
    }

    function Start(){
        var Generated = [];
        var level = Number(document.getElementsByClassName('blend-level-input')[0].value);

        function CleaTable(){
            for (i=0;i<rowLength;i++) {table.deleteRow(0);}
        }
        CleaTable();

        function Begin(w){ // add each information to the (beginning) of each information.
            for (n=0;n<information_length;n++){
                password = w + array_data[n];
                if (!(Generated.includes(password))) {
                    Generated.push(password) // (w) is the current working word and (array_data[n])..
                    // is a single information from the list to add to the (start) of the current...
                    // working information (in an order ofcourse).
                    NewPassword(password, 'low')
                }
            }
        }

        function End(w){ // add each information to the (end) of each information.
            for (n=0;n<information_length;n++){
                password = array_data[n] + w;
                if (!(Generated.includes(password))) {
                    Generated.push(password); // (w) is the current working word and (array_data[n])..
                    // is a single information from the list to add to the (end) of the current...
                    // working information (in an order ofcourse).
                    NewPassword(password, 'low')
                }
            }
        }

        function FromTheHalf(w){ // add each information in the half of each information.
            var half_int = ~~(w.length / 2); // ~~ convert the number to integer instead of float
            var half_left = w.substring(0, half_int);
            var half_right = w.substring(half_int, w.length);
        
            for (n=0;n<2;n++){
                password = half_left + array_data[n] + half_right;
                if (!(Generated.includes(password))) {
                    Generated.push(half_left + array_data[n] + half_right); // length of (hello world) is 11 characters
                    NewPassword(password, 'low medium')
                }
            }
        }

        function FromTheQuarterd(w){
            var quarter_int = ~~(w.length / 4);
            var quarter_left = w.substring(0, quarter_int);
            var quarter_left2 = w.substring(quarter_int, quarter_int*2);
            var quarter_right = w.substring(quarter_int*2, quarter_int*3);
            var quarter_right2 = w.substring(quarter_int*3, w.length);

            for (n=0;n<4;n++){
                password = quarter_left + array_data[n] + quarter_left2 + array_data[n] + quarter_right + quarter_right2;
                if (!(Generated.includes(password))){
                    Generated.push(password); // length of (hello world) is 11 characters
                    NewPassword(password, 'medium')
                }
            }
        }

        for (i=0;i<information_length;i++){ // itterate into each information to mix it with other inforamtions.
            word = array_data[i]; // set current working word to a variable called word.

            if (level === 1){
                if (information_length > 0){
                    Begin(word);
                    End(word);
                }
                else {alert('Level 1 required at less 1 information.');break;}
            }
            else if (level === 2){
                if (information_length > 1){
                    Begin(word);
                    End(word);
                    FromTheHalf(word);
                }
                else {alert('Level 2 required at less 2 information.');break;}
            }
            else if (level === 3){
                if (information_length > 3){
                    Begin(word);
                    End(word);
                    FromTheHalf(word);
                    FromTheQuarterd(word);
                }
                else {alert('Level 3 required at less 4 information.');break;}
            }
            else {
                alert(`${level} is not a valid level\n
            valid mixing levels are 1-3.`);
            break;
            }
        }
    }
    if (!(information_length < 1)){
        Start();
    }
    else{alert("No information was provided.")}
}

function Save(){
    var PASSWORDS = '';
    var rows = document.getElementById('pass-table').rows;
    var rows_len = rows.length;
    for (i=0;i<rows_len;i++){
        var password = document.getElementById('pass-table').rows[i].cells[1].innerText;
        PASSWORDS += password + '\n';
    }
    navigator.clipboard.writeText(PASSWORDS);
}