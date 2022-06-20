


var Now = new Date(); // 현재 날짜 및 시간
var nowMonth = Now.getMonth() + 1; // 월
var nowDay = Now.getDate(); // 일
var nowHour = Now.getHours(); // 시
var nowMins = Now.getMinutes(); // 분

function pluszero(time) {
    var time = time.toString(); // 시간을 숫자에서 문자로 바꿈
    if (time.length < 2) { //2자리 보다 작다면
        time = '0' + time; //숫자앞 0을 붙여줌
        return time; //값을 내보냄
    } else {
        return time; //2자리라면 값을 내보냄
    }
}
nowMonth = pluszero(nowMonth); //만들었던 함수 적용
nowDay = pluszero(nowDay);
nowHour = pluszero(nowHour);
nowMins = pluszero(nowMins);

var nowtime = nowMonth + nowDay + nowHour + nowMins; // 월+일+시+분


var startdate = 04010000;
var enddate = 05310000;

$(document).ready(() => {
    if (eval(startdate) > eval(nowtime) || eval(enddate) < eval(nowtime)) { //지금이 시작시간보다 작거나, 종료시간보다 크면 
        console.log("된 거?");
        $("#lastGather").attr("disabled", true);
    } else {
        console.log("그럴 리가 ㅋ");
    }
})


export default lasttime;



