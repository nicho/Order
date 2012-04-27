
var PageName = '用户管理';
var PageId = '2888c6c890be42e18ff53daf7a941518'
var PageUrl = '用户管理.html'
document.title = '用户管理';
var PageNotes = 
{
"pageName":"用户管理",
"showNotesNames":"False"}
var $OnLoadVariable = '';

var $CSUM;

var hasQuery = false;
var query = window.location.hash.substring(1);
if (query.length > 0) hasQuery = true;
var vars = query.split("&");
for (var i = 0; i < vars.length; i++) {
    var pair = vars[i].split("=");
    if (pair[0].length > 0) eval("$" + pair[0] + " = decodeURIComponent(pair[1]);");
} 

if (hasQuery && $CSUM != 1) {
alert('Prototype Warning: The variable values were too long to pass to this page.\nIf you are using IE, using Firefox will support more data.');
}

function GetQuerystring() {
    return '#OnLoadVariable=' + encodeURIComponent($OnLoadVariable) + '&CSUM=1';
}

function PopulateVariables(value) {
    var d = new Date();
  value = value.replace(/\[\[OnLoadVariable\]\]/g, $OnLoadVariable);
  value = value.replace(/\[\[PageName\]\]/g, PageName);
  value = value.replace(/\[\[GenDay\]\]/g, '4');
  value = value.replace(/\[\[GenMonth\]\]/g, '3');
  value = value.replace(/\[\[GenMonthName\]\]/g, '三月');
  value = value.replace(/\[\[GenDayOfWeek\]\]/g, '星期日');
  value = value.replace(/\[\[GenYear\]\]/g, '2012');
  value = value.replace(/\[\[Day\]\]/g, d.getDate());
  value = value.replace(/\[\[Month\]\]/g, d.getMonth() + 1);
  value = value.replace(/\[\[MonthName\]\]/g, GetMonthString(d.getMonth()));
  value = value.replace(/\[\[DayOfWeek\]\]/g, GetDayString(d.getDay()));
  value = value.replace(/\[\[Year\]\]/g, d.getFullYear());
  return value;
}

function OnLoad(e) {

}

var u115 = document.getElementById('u115');

var u122 = document.getElementById('u122');
gv_vAlignTable['u122'] = 'top';
var u21 = document.getElementById('u21');

var u137 = document.getElementById('u137');
gv_vAlignTable['u137'] = 'top';
var u32 = document.getElementById('u32');

var u130 = document.getElementById('u130');
gv_vAlignTable['u130'] = 'top';
var u7 = document.getElementById('u7');

var u2 = document.getElementById('u2');

var u79 = document.getElementById('u79');
gv_vAlignTable['u79'] = 'top';
var u4 = document.getElementById('u4');

var u17 = document.getElementById('u17');

var u135 = document.getElementById('u135');
gv_vAlignTable['u135'] = 'top';
var u151 = document.getElementById('u151');

var u42 = document.getElementById('u42');
gv_vAlignTable['u42'] = 'top';
var u55 = document.getElementById('u55');

var u101 = document.getElementById('u101');
gv_vAlignTable['u101'] = 'top';
var u14 = document.getElementById('u14');

var u48 = document.getElementById('u48');
gv_vAlignTable['u48'] = 'top';
var u105 = document.getElementById('u105');

var u27 = document.getElementById('u27');
gv_vAlignTable['u27'] = 'center';
var u138 = document.getElementById('u138');

var u52 = document.getElementById('u52');
gv_vAlignTable['u52'] = 'top';
var u20 = document.getElementById('u20');

var u67 = document.getElementById('u67');
gv_vAlignTable['u67'] = 'top';
var u65 = document.getElementById('u65');
gv_vAlignTable['u65'] = 'top';
var u120 = document.getElementById('u120');
gv_vAlignTable['u120'] = 'top';
var u110 = document.getElementById('u110');
gv_vAlignTable['u110'] = 'top';
var u6 = document.getElementById('u6');

var u108 = document.getElementById('u108');
gv_vAlignTable['u108'] = 'top';
var u37 = document.getElementById('u37');

var u62 = document.getElementById('u62');

var u141 = document.getElementById('u141');
gv_vAlignTable['u141'] = 'top';
var u11 = document.getElementById('u11');

var u75 = document.getElementById('u75');
gv_vAlignTable['u75'] = 'top';
var u133 = document.getElementById('u133');
gv_vAlignTable['u133'] = 'top';
var u34 = document.getElementById('u34');

var u68 = document.getElementById('u68');

var u89 = document.getElementById('u89');
gv_vAlignTable['u89'] = 'top';
var u47 = document.getElementById('u47');

var u72 = document.getElementById('u72');

var u103 = document.getElementById('u103');
gv_vAlignTable['u103'] = 'top';
var u99 = document.getElementById('u99');
gv_vAlignTable['u99'] = 'top';
var u66 = document.getElementById('u66');

var u112 = document.getElementById('u112');
gv_vAlignTable['u112'] = 'top';
var u44 = document.getElementById('u44');
gv_vAlignTable['u44'] = 'top';
var u78 = document.getElementById('u78');

var u57 = document.getElementById('u57');

var u16 = document.getElementById('u16');
gv_vAlignTable['u16'] = 'center';
var u125 = document.getElementById('u125');

var u41 = document.getElementById('u41');

var u149 = document.getElementById('u149');
gv_vAlignTable['u149'] = 'top';
var u54 = document.getElementById('u54');
gv_vAlignTable['u54'] = 'top';
var u140 = document.getElementById('u140');

var u38 = document.getElementById('u38');
gv_vAlignTable['u38'] = 'top';
var u26 = document.getElementById('u26');

var u119 = document.getElementById('u119');

var u85 = document.getElementById('u85');
gv_vAlignTable['u85'] = 'top';
var u51 = document.getElementById('u51');

var u10 = document.getElementById('u10');
gv_vAlignTable['u10'] = 'center';
var u100 = document.getElementById('u100');

var u77 = document.getElementById('u77');
gv_vAlignTable['u77'] = 'top';
var u144 = document.getElementById('u144');

var u31 = document.getElementById('u31');

var u82 = document.getElementById('u82');

var u36 = document.getElementById('u36');
gv_vAlignTable['u36'] = 'top';
var u30 = document.getElementById('u30');
gv_vAlignTable['u30'] = 'center';
var u95 = document.getElementById('u95');
gv_vAlignTable['u95'] = 'top';
var u61 = document.getElementById('u61');

var u116 = document.getElementById('u116');
gv_vAlignTable['u116'] = 'top';
var u88 = document.getElementById('u88');

var u123 = document.getElementById('u123');

var u114 = document.getElementById('u114');
gv_vAlignTable['u114'] = 'top';
var u33 = document.getElementById('u33');
gv_vAlignTable['u33'] = 'center';
var u92 = document.getElementById('u92');

var u46 = document.getElementById('u46');
gv_vAlignTable['u46'] = 'top';
var u126 = document.getElementById('u126');

var u71 = document.getElementById('u71');
gv_vAlignTable['u71'] = 'top';
var u5 = document.getElementById('u5');
gv_vAlignTable['u5'] = 'center';
var u98 = document.getElementById('u98');

var u127 = document.getElementById('u127');
gv_vAlignTable['u127'] = 'top';
var u43 = document.getElementById('u43');

var u56 = document.getElementById('u56');
gv_vAlignTable['u56'] = 'top';
var u150 = document.getElementById('u150');

var u74 = document.getElementById('u74');

var u142 = document.getElementById('u142');

var u106 = document.getElementById('u106');

var u40 = document.getElementById('u40');
gv_vAlignTable['u40'] = 'top';
var u139 = document.getElementById('u139');
gv_vAlignTable['u139'] = 'top';
var u87 = document.getElementById('u87');
gv_vAlignTable['u87'] = 'top';
var u53 = document.getElementById('u53');

var u104 = document.getElementById('u104');

var u121 = document.getElementById('u121');

var u19 = document.getElementById('u19');
gv_vAlignTable['u19'] = 'center';
var u109 = document.getElementById('u109');

var u84 = document.getElementById('u84');

var u50 = document.getElementById('u50');
gv_vAlignTable['u50'] = 'top';
var u97 = document.getElementById('u97');
gv_vAlignTable['u97'] = 'top';
var u63 = document.getElementById('u63');
gv_vAlignTable['u63'] = 'top';
var u76 = document.getElementById('u76');

var u134 = document.getElementById('u134');

var u81 = document.getElementById('u81');
gv_vAlignTable['u81'] = 'top';
var u128 = document.getElementById('u128');
gv_vAlignTable['u128'] = 'top';
var u94 = document.getElementById('u94');

var u60 = document.getElementById('u60');

var u102 = document.getElementById('u102');

var u9 = document.getElementById('u9');

var u73 = document.getElementById('u73');
gv_vAlignTable['u73'] = 'top';
var u113 = document.getElementById('u113');

var u147 = document.getElementById('u147');
gv_vAlignTable['u147'] = 'top';
var u91 = document.getElementById('u91');
gv_vAlignTable['u91'] = 'top';
var u131 = document.getElementById('u131');

var u64 = document.getElementById('u64');

var u70 = document.getElementById('u70');

var u24 = document.getElementById('u24');

var u23 = document.getElementById('u23');

var u117 = document.getElementById('u117');

var u13 = document.getElementById('u13');
gv_vAlignTable['u13'] = 'center';
var u29 = document.getElementById('u29');

var u111 = document.getElementById('u111');

var u132 = document.getElementById('u132');

var u129 = document.getElementById('u129');
gv_vAlignTable['u129'] = 'top';
var u86 = document.getElementById('u86');

var u58 = document.getElementById('u58');
gv_vAlignTable['u58'] = 'top';
var u39 = document.getElementById('u39');

var u0 = document.getElementById('u0');

var u145 = document.getElementById('u145');
gv_vAlignTable['u145'] = 'top';
var u83 = document.getElementById('u83');
gv_vAlignTable['u83'] = 'top';
var u8 = document.getElementById('u8');
gv_vAlignTable['u8'] = 'center';
var u3 = document.getElementById('u3');
gv_vAlignTable['u3'] = 'center';
var u96 = document.getElementById('u96');

var u146 = document.getElementById('u146');

var u15 = document.getElementById('u15');

var u49 = document.getElementById('u49');

var u124 = document.getElementById('u124');
gv_vAlignTable['u124'] = 'top';
var u80 = document.getElementById('u80');

var u1 = document.getElementById('u1');

var u148 = document.getElementById('u148');

var u93 = document.getElementById('u93');
gv_vAlignTable['u93'] = 'top';
var u12 = document.getElementById('u12');

var u25 = document.getElementById('u25');
gv_vAlignTable['u25'] = 'center';
var u59 = document.getElementById('u59');

var u118 = document.getElementById('u118');
gv_vAlignTable['u118'] = 'top';
var u90 = document.getElementById('u90');

var u18 = document.getElementById('u18');

var u136 = document.getElementById('u136');

var u45 = document.getElementById('u45');

var u22 = document.getElementById('u22');
gv_vAlignTable['u22'] = 'center';
var u143 = document.getElementById('u143');
gv_vAlignTable['u143'] = 'top';
var u107 = document.getElementById('u107');

var u35 = document.getElementById('u35');

var u69 = document.getElementById('u69');
gv_vAlignTable['u69'] = 'top';
var u28 = document.getElementById('u28');

if (window.OnLoad) OnLoad();
