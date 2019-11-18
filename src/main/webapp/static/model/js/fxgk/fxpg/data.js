//jha Scl 风险评估
var possibility=[{"level":5,"standard":"等级5：在现场没有采取防范、监测、保护、控制措施，或危害的发生不能被发现（没有监测系统），或在正常情况下经常发生此类事故或事件。"},
                 {"level":4,"standard":"等级4：危害的发生不容易被发现，现场没有检测系统，也未发生过任何监测，或在现场有控制措施，但未有效执行或控制措施不当，或危害常发生或在预期情况下发生。"},
                 {"level":3,"standard":"等级3：没有保护措施（如没有保护装置、没有个人防护用品等），或未严格按操作程序执行，或危害的发生容易被发现（现场有监测系统），或曾经作过监测，或过去曾经发生类似事故或事件，或在异常情况下类似事故或事件。"},
                 {"level":2,"standard":"等级2：危害一旦发生能及时发现，并定期进行监测，或现场有防范控制措施，并能有效执行，或过去偶尔发生事故或事件。"},
                 {"level":1,"standard":"等级1：有充分、有效的防范、控制、监测、保护措施，或员工安全卫生意识相当高，严格执行操作规程。极不可能发生事故或事件。"}];


var severity=[{"level":5,"text":"等级5","law":"违反法律、法规和标准","person":"死亡","propertyloss":">50","shutdown":"部分装置（>2套）或设备停工","companyimage":"重大国际国内影响"},
              {"level":4,"text":"等级4","law":"潜在违反法规和标准","person":"丧失劳动能力","propertyloss":">25","shutdown":"2套装置停工、或设备停工","companyimage":"行业内、省内影响"},
              {"level":3,"text":"等级3","law":"不符合上级公司或行业的安全方针、制度、规定等","person":"截肢、骨折、听力丧失、慢性病","propertyloss":">10","shutdown":"1套装置停工或设备","companyimage":"地区影响"},
              {"level":2,"text":"等级2","law":"不符合公司的安全操作程序、规定","person":"轻微受伤、间歇不舒服","propertyloss":"<10","shutdown":"受影响不大，几乎不停工","companyimage":"公司及周边范围"},
              {"level":1,"text":"等级1","law":"完全符合","person":"无伤亡","propertyloss":"无损失","shutdown":"没有停工","companyimage":"形象没有受损"}];

var measure=[{"min":20,"max":25,"level":"极度危险","measure":"在采取措施降低危害前，不能继续作业，对改进措施进行评估","deadline":"立刻"},
             {"min":15,"max":16,"level":"高度危险","measure":"采取紧急措施降低风险，建立运行控制程序，定期检查、测量及评估","deadline":"立即或近期整改"},
             {"min":6,"max":12,"level":"一般危险","measure":"可考虑建立目标、建立操作规程，加强培训及沟通","deadline":"2年内治理"},
             {"min":1,"max":5,"level":"低度危险","measure":"可考虑建立操作规程、作业指导书但需定期检查或无需采用控制措施，但需保存记录","deadline":"有条件、有经费时治理"}];

//LEC 风险评估
var lecL=   [{"score":10,"possibility":"完全可以预料"},
	        {"score":6,"possibility":"相当可能"},
	        {"score":3,"possibility":"可能，但不经常"},
	        {"score":1,"possibility":"可能性小，完全意外"},
	        {"score":0.5,"possibility":"很不可能，可以设想"},
	        {"score":0.2,"possibility":"极不可能"},
	        {"score":0.1,"possibility":"实际不可能"}];

var lecE=[{"score":10,"frequency":"连续暴露"},
          {"score":6,"frequency":"每天工作时间内暴露"},
          {"score":3,"frequency":"每周一次或偶然暴露"},
          {"score":2,"frequency":"每月一次暴露"},
          {"score":1,"frequency":"每年几次暴露"},
          {"score":0.5,"frequency":"非常罕见暴露"}];

var lecC=[{"score":100,"consequence":"10人以上死亡"},
          {"score":40,"consequence":"3～9人死亡"},
          {"score":15,"consequence":"1～2人死亡"},
          {"score":7,"consequence":"严重"},
          {"score":3,"consequence":"重大，伤残"},
          {"score":1,"consequence":"引人注意"}];

var lecD=[{"min":320,"max":10001,"severity":"极度危险，不能继续作业","level":"1"},
          {"min":160,"max":320,"severity":"高度危险，要立即整改","level":"1"},
          {"min":70,"max":160,"severity":"中度危险，需要整改","level":"2"},
          {"min":20,"max":70,"severity":"一般危险，需要注意","level":"3"},
          {"min":0,"max":20,"severity":"低度危险，可以接受","level":"4"}];

//危险度风险评估
//物质
var WxdMatter=[{"score":10,"text":"A（10分）","matter":"A（10分）:①甲类可燃气体;②甲A类物质及液态烃类;③甲类固体;④极度危害介质"},
               {"score":5,"text":"B（5分）","matter":"B（5分）:①乙类可燃气体;②甲B、乙A类可燃液体;③乙类固体;④高度危害介质"},
               {"score":2,"text":"C（2分）","matter":"C（2分）:①乙B、丙A、丙B类可燃液体;②丙类固体;③中、轻度危害介质"},
               {"score":"0","text":"D（0分）","matter":"D（0分）:不属上述之A、B、C项之物质"}];
//capacity 容量
var WxdCapacity=[{"score":10,"text":"A（10分）","capacity":"A（10分）:①气体1000m3以上;②液体100m3以上"},
               {"score":5,"text":"B（5分）","capacity":"B（5分）:①气体500～1000m3;②液体50～100m3"},
               {"score":2,"text":"C（2分）","capacity":"C（2分）:①气体100～500m3;②液体10～50m3"},
               {"score":"0","text":"D（0分）","capacity":"D（0分）:①气体﹤100m3;②液体﹤10m3"}];
//temperature 温度
var WxdTemperature=[{"score":10,"text":"A（10分）","temperature":"A（10分）:1000℃以上使用，其操作温度在燃点以上"},
                 {"score":5,"text":"B（5分）","temperature":"B（5分）:①1000℃以上使用，其操作温度在燃点以下;②250～1000℃使用，其操作温度在燃点以上"},
                 {"score":2,"text":"C（2分）","temperature":"C（2分）:①250～1000℃使用，其操作温度在燃点以下;②在低于250℃使用，其操作温度在燃点以上"},
                 {"score":"0","text":"D（0分）","temperature":"D（0分）:在低于250℃使用，其操作温度在燃点以下"}];
//pressure 压力
var WxdPressure=[{"score":10,"text":"A（10分）","pressure":"A（10分）:100MPa"},
                    {"score":5,"text":"B（5分）","pressure":"B（5分）:20～100MPa"},
                    {"score":2,"text":"C（2分）","pressure":"C（2分）:1～20MPa"},
                    {"score":"0","text":"D（0分）","pressure":"D（0分）:1MPa以下"}];
//operation 操作
var WxdOperation=[{"score":10,"text":"A（10分）","operation":"A（10分）:①临界放热和特别剧烈的放热反应操作;②在爆炸极限范围内或其附近的操作"},
                 {"score":5,"text":"B（5分）","operation":"B（5分）:①中等放热反应（如脂化、加成、氧化、聚合、缩合等）操作;②系统进入空气或不纯物质，可能发生的危险、操作;③使用粉状或雾状物质，有可能发生粉尘爆炸的操作;④单批式操作"},
                 {"score":2,"text":"C（2分）","operation":"C（2分）:①轻微放热反应（如加氢、水合、异构化、磺化、中和等）操作;②在精制过程中伴有化学反应;③单批式操作，但开始使用机械等手段进行程序操作;④有一定危险的操作"},
                 {"score":"0","text":"D（0分）","operation":"D（0分）:无危险的操作"}];

var WxdLevel=[{"min":0,"max":10,"severity":"低度危险"},
              {"min":11,"max":15,"severity":"中度危险"},
              {"min":16,"max":50,"severity":"极度危险"}];



