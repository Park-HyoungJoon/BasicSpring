let Now = new Date();
let nowMonth = Now.getMonth() + 1;
let nowDay = Now.getDate();
let nowHour = Now.getHours();
let nowMins = Now.getMinutes();

function pluszero(time){
    let time = time.toString();
    if(time.length < 2) {
        time ='0' + time;
        return time;
    }else{
        return time;
    }
}

nowMonth = pluszero(nowMonth);
nowDay = pluszero(nowDay);
nowHour = pluszero(nowHour);
nowMins = pluszero(nowMins);

let nowtime = nowMonth + nowDay + nowHour + nowMins;


export default nowtime;