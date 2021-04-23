window.onload = function () {
    (autoComplete = {
        pop_len: 10,
        pop_cn: 'autoDis',
        hover_cn: 'cur',
        source: 'ACC_pS79,ACC1,ACETYLATUBULINLYS40,ACVRL1,ADAR1,AKT,AKT_pS473,AKT_pT308,alpha-Catenin,AMPKALPHA,AMPKALPHA_pT172,ANNEXIN1,ANNEXINVII,AR,ARAF,ARAF_pS299,ARHI,ARID1A,ASNS,ATM,AXL,BAD_pS112,BAK,BAP1C4,BAX,BCL2,BCL2A1,BCLXL,BECLIN,BETACATENIN,BID,BIM,BRAF,BRAF_pS445,BRCA2,BRD4,CA9,CABL,CASPASE3,CASPASE7CLEAVEDD198,CASPASE8,CASPASE9,Caspase-9,CAVEOLIN1,CD20,CD26,CD274,CD31,CD49B,CDK1,CDK1_pY15,CHK1,CHK1_pS296,CHK1_pS345,CHK2,CHK2_pT68,CHROMOGRANINANTERM,CIAP,CJUN_pS73,CK5,CKIT,CLAUDIN7,CMET,CMET_pY1235,CMYC,COG3,COLLAGENVI,CRAF,CRAF_pS338,CTLA4,CYCLINB1,CYCLIND1,CYCLINE1,CYCLINE2,DIRAS3,DJ1,DUSP4,DVL3,E2F1,ECADHERIN,EEF2,EEF2K,EGFR,EGFR_pY1068,EGFR_pY1173,EIF4E,EIF4G,ENY2,EPPK1,ERALPHA,ERALPHA_pS118,ERCC1,ERCC5,ERK2,ETS1,EZH2,FASN,FIBRONECTIN,FOXM1,FOXO3A,FOXO3A_pS318S321,G6PD,GAB2,GAPDH,GATA3,GATA6,GSK3_pS9,GSK3ALPHABETA,GSK3ALPHABETA_pS21S9,GYS,GYS_pS641,HER2,HER2_pY1248,HER3,HER3_pY1289,HEREGULIN,HIF1ALPHA,HSP70,IGF1R_pY1135Y1136,IGFBP2,INPP4B,IRF1,IRS1,JAB1,JAK2,JNK_pT183Y185,JNK2,KAT2A,KEAP1,KU80,LCK,LCN2A,LDHA,LDHB,LKB1,MACC1,MAPK_pT202Y204,MEK1,MEK1_pS217S221,MIG6,MITOCHONDRIA,MRE11,MSH2,MSH6,MTOR,MTOR_pS2448,MYH11,MYOSINIIA,MYOSINIIA_pS1943,NAPSINA,NCADHERIN,NDRG1_pT346,NF2,NFKBP65_pS536,NOTCH1,NRAS,NRF2,P16INK4A,P21,P27,P27_pT157,P27_pT198,P38,P38_pT180Y182,P38MAPK,P53,P62LCKLIGAND,P63,P70S6K_pT389,P70S6K1,P90RSK,P90RSK_pT359S363,PAI1,PARP1,PARPAB3,PARPCLEAVED,PAXILLIN,PCADHERIN,PCNA,PDCD1,PDCD4,PDK1,PDK1_pS241,PDL1,PEA15,PEA15_pS116,PI3KP110ALPHA,PI3KP85,PKCALPHA,PKCALPHA_pS657,PKCDELTA_pS664,PKCPANBETAII_pS660,PKM2,PR,PRAS40_pT246,PRDX1,PREX1,PTEN,PYGB,PYGL,PYGM,RAB11,RAB25,RAD50,RAD51,RAPTOR,RB,RB_pS807S811,RBM15,RET_pY905,RICTOR,RICTOR_pT1135,S6,S6_pS235S236,S6_pS240S244,SCD1,SETD2,SF2,SHC_pY317,SHP2_pY542,SLC1A5,SMAC,SMAD1,SMAD3,SMAD4,SNAIL,SRC,SRC_pY416,SRC_pY527,STAT3_pY705,STAT5ALPHA,STATHMIN,SYK,SYNAPTOPHYSIN,TAZ,TFRC,THYMIDILATESYNTHASE,TIGAR,TRANSGLUTAMINASE,TSC1,TTF1,TUBERIN,TUBERIN_pT1462,VEGFR2,VHL,X1433BETA,X1433EPSILON,X1433ZETA,X4EBP1,X4EBP1_pS65,X4EBP1_pT37T46,X4EBP1_pT70,X53BP1,XBP1,XRCC1,YAP,YAP_pS127,YB1,YB1_pS102'.split(','),
        init: function () {
            this.setDom();
            return this;
        },
        bind: function (x) {
            if (x.getAttribute('type') != 'text' || x.nodeName != 'INPUT')
                return null;
            var self = this;
            x.onkeyup = function (e) {
                e = e || window.event;
                var lis = self.pop.getElementsByTagName('li'), lens = self.pop.getElementsByTagName('li').length,
                    n = lens, temp;
                if (e.keyCode == 38) { //����up�������� 
                    if (self.pop.style.display != 'none') {
                        for (var i = 0; i < lens; i++) { //����������ݣ��ж��Ƿ�ѡ�� 
                            if (lis[i].className)
                                temp = i;
                            else
                                n--;
                        }
                        if (n == 0) { //���û�б�ѡ�е�liԪ�أ���ѡ�����һ�� 
                            lis[lens - 1].className = self.hover_cn;
                            this.value = lis[lens - 1].innerHTML;
                        } else { //����б�ѡ�е�Ԫ�أ���ѡ����һ��Ԫ�ز���ֵ��input 
                            if (lis[temp] == lis[0]) { //���ѡ�е�Ԫ���ǵ�һ�����ӽڵ����������һ��ѡ�� 
                                lis[lens - 1].className = self.hover_cn;
                                this.value = lis[lens - 1].innerHTML;
                                lis[temp].className = '';
                            } else {
                                lis[temp - 1].className = self.hover_cn;
                                this.value = lis[temp - 1].innerHTML;
                                lis[temp].className = '';
                            }
                        }
                    } else //���������û����ʾ��ִ�в������������ʾ������ 
                        self.insert(this);
                } else if (e.keyCode == 40) { //down�������£�ԭ��ͬup�� 
                    if (self.pop.style.display != 'none') {
                        for (var i = 0; i < lens; i++) {
                            if (lis[i].className)
                                temp = i;
                            else
                                n--;
                        }
                        if (n == 0) {
                            lis[0].className = self.hover_cn;
                            this.value = lis[0].innerHTML;
                        } else {
                            if (lis[temp] == lis[lens - 1]) {
                                lis[0].className = self.hover_cn;
                                this.value = lis[0].innerHTML;
                                lis[temp].className = '';
                            } else {
                                lis[temp + 1].className = self.hover_cn;
                                this.value = lis[temp + 1].innerHTML;
                                lis[temp].className = '';
                            }
                        }
                    } else
                        self.insert(this);
                } else //������µļ��Ȳ���up�ֲ���down��ôֱ��ȥƥ�����ݲ����� 
                    self.insert(this);
            };
            x.onblur = function () { //����ӳٴ�������Ϊ���ʧȥ�����ʱ���ǵ��ѡ�����ݵ�ʱ��ᷢ�����޷���������¼� 
                setTimeout(function () {
                    self.pop.style.display = 'none';
                }, 300);
            };
            return this;
        },
        setDom: function () {
            var self = this;
            var dom = document.createElement('div'), frame = document.createElement('iframe'),
                ul = document.createElement('ul');
            document.body.appendChild(dom);
            with (frame) { //������ie6����סselectԪ�� 
                setAttribute('frameborder', '0');
                setAttribute('scrolling', 'no');
                style.cssText = 'z-index:-1;position:absolute;left:0;top:0;'
            }
            with (dom) { //�Ե�����liԪ�ذ�onmouseover��onmouseover 
                className = this.pop_cn;
                appendChild(frame);
                appendChild(ul);
                onmouseover = function (e) { //��liԪ�ػ�û�м��ص�ʱ��Ͱ����������ͨ���ж�target�Ƿ���liԪ�ؽ��д��� 
                    e = e || window.event;
                    var target = e.srcElement || e.target;
                    if (target.tagName == 'LI') { //�����ʽǰ�Ȱ����е�li��ʽȥ���������õ���һ��͵���ķ�ʽ��û�е���дremoveClass���� 
                        for (var i = 0, lis = self.pop.getElementsByTagName('li'); i < lis.length; i++)
                            lis[i].className = '';
                        target.className = self.hover_cn; //Ҳû��дaddClass������ֱ�Ӹ�ֵ�� 
                    }
                };
                onmouseout = function (e) {
                    e = e || window.event;
                    var target = e.srcElement || e.target;
                    if (target.tagName == 'LI')
                        target.className = '';
                };
            }
            this.pop = dom;
        },
        insert: function (self) {
            var bak = [], s, li = [], left = 0, top = 0, val = self.value;
            for (var i = 0, leng = this.source.length; i < leng; i++) { //�ж�input�������Ƿ�������Դ�������һ�� 
                if (!!val && val.length <= this.source[i].length && (this.source[i].substr(0, val.length) == val || this.source[i].substr(0, val.length) == val.toUpperCase())) {
                    bak.push(this.source[i]);
                }
            }
            if (bak.length == 0) { //���û��ƥ������������ص����� 
                this.pop.style.display = 'none';
                return false;
            } //��������㶨λ����֮ǰҲ����ѭ��offsetParent��������ie��ff�²��ܴ󣨿�����ʹ�÷�ʽ�����������Ը������getBoundingClientRect 
            left = self.getBoundingClientRect().left + document.documentElement.scrollLeft;
            top = self.getBoundingClientRect().top + document.documentElement.scrollTop + self.offsetHeight;
            with (this.pop) {
                style.cssText = 'width:' + self.offsetWidth + 'px;' + 'position:absolute;left:' + left + 'px;top:' + top + 'px;display:none;';
                getElementsByTagName('iframe')[0].setAttribute('width', self.offsetWidth);
                getElementsByTagName('iframe')[0].setAttribute('height', self.offsetHeight);
                onclick = function (e) {
                    e = e || window.event;
                    var target = e.srcElement || e.target;
                    if (target.tagName == 'LI')
                        self.value = target.innerHTML;
                    this.style.display = 'none';
                };
            }
            s = bak.length > this.pop_len ? this.pop_len : bak.length;
            for (var i = 0; i < s; i++)
                li.push('<li>' + bak[i] + '</li>');
            this.pop.getElementsByTagName('ul')[0].innerHTML = li.join('');
            this.pop.style.display = 'block';
        }
    }).init().bind(document.getElementById('gene'));
}