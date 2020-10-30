var config = {
    apiKey: "AIzaSyCeC7MjgPsyqEiMwNb3kLj_d9YKTkcW03g",
    authDomain: "job-search-i4gic.firebaseapp.com",
    databaseURL: "https://job-search-i4gic.firebaseio.com",
    projectId: "job-search-i4gic",
    storageBucket: "job-search-i4gic.appspot.com",
    messagingSenderId: "239085417537"
};
firebase.initializeApp(config);

var root= firebase.database().ref('sign-up-account');
var j;
root.on("value",snap=>{
      j=snap.numChildren();
});
document.getElementById('sign-up').addEventListener('submit', submitForm);

function submitForm(e){
    e.preventDefault();
  var txtEmail= getInputVal('txtEmail');
  var txtPass= getInputVal('txtPass');
  var txtVerPass= getInputVal('txtVerPass');
  saveMessage(txtEmail,txtPass,txtVerPass,j);
//   if(x){
//     j++;
//   }
//   saveMessage(txtEmail,txtPass,txtVerPass,x);
}

function getInputVal(id){
    return document.getElementById(id).value;
}

function saveMessage(txtEmail,txtPass,txtVerPass,j){
    var a=txtPass.length;
    var b=txtVerPass.length;
    var c=1;
    var root= firebase.database().ref('sign-up-account');
    var f=1;
    if(f==1){
    root.on("value" ,snap => {
        var d=Object.keys(snap.val());
        console.log((snap.child('User1').child('Email').val()));
        console.log(txtEmail);
        for(var i=1 ; i <= d.length; i++){
            // String m=txtEmail;
            // String n=snap.child('User'+i).child('Email').val();
           if((snap.child('User'+i).child('Email').val()).equals(txtEmail) == true){
           
            document.getElementById('demo').innerHTML="This email address is already exist!";
            c=0;
            break;
           }
        }
    });
}
        if(txtEmail==null || txtEmail=="",txtPass==null || txtPass=="",txtVerPass==null || txtVerPass==""){
            document.getElementById('demo').innerHTML="Fill all info required to create an account";
            c=0;
        }
        if(a!=b){
            document.getElementById('demo').innerHTML="Password & ConfirmPassword not match!";
        }
        else{
            for(var i=0;i<a;i++){
                if(txtPass.charAt(i)!=txtVerPass.charAt(i)){
                  document.getElementById('demo').innerHTML="Password & ConfirmPassword not match!";
                  c=0;
                  break;
                }
                else{
                        c=1;
}
    }
            if(c==1){
                j++;
                var u="User"+j;
                var info=root.child(u);
                info.set({
                Email: txtEmail,
                Password: txtPass,
                ConfirmPassword: txtVerPass,
            }); 
            document.getElementById('demo').innerHTML="";
            document.getElementById('successfulsignup').innerHTML="You have successfully created the account";
            setTimeout(function(){window.location.href='jobs-search-account.html'},1000);      
            return true;     

            }
        }
    }  
