package Oppgave2;

import javax.lang.model.element.Element;

/**
 * Created by asdfLaptop on 13.09.2016.
 */
public class Kø {
    private Object[] tab;
    private int start = 0;
    private int slutt = 0;
    private int antall = 0;

    public Kø(int str) {
        tab = new Object[str];
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean full() {
        return antall == tab.length;
    }

    public void leggIKø(Object e) {
        if(full()) return;
        tab[slutt] = e;
        slutt = (slutt+1)%tab.length;
        antall++;
    }

    public Object nesteIKø() {
        if(!tom()) {
            Object e = tab[start];
            start = (start+1)%tab.length;
            antall--;
            return e;
        } else return null;
    }

    public Object sjekkKø() {
        if(!tom()) {
            return tab[start];
        } else return null;
    }

    public Kø omformTilRpn(Kø vanlig) {
        double tall = 0;
        Stakk s = new Stakk(100);
        Kø kø = new Kø(100);
        while (!vanlig.tom()) {
            Element el = (Element)(vanlig.nesteIKø());
            switch (el.type()){
                case 't':
                    tall = tall*10 + ((Tall)el).verdi();
                    if(vanlig.tom() || vanlig.sjekkKø() instanceof Operator) {
                        kø.leggIKø(new Tall(tall));
                        tall = 0;
                    }
                    break;
                case '+':
                case '-';
                    if(!s.tom() &&((Element)s.sjekkStakk())).type()!='('){
                    Element e2 = (Element)(s.pop());
                    kø.leggIKø(e2);
                    if((e2.type()=='*' || e2.type()=='/')&&!s.tom() && ((Element)(s.sjekkStakk())).type()!='('){
                        kø.leggIKø(s.pop());
                    }
                }
                s.push(el);
                break;
                case '*':
                case '/':
                    if(!s.tom()){
                        Element e2 = (Element)(s.sjekkStakk());
                        if(e2.type()=='*' || e2.type() == '/') {
                            kø.leggIKø(s.pop());
                        }
                        s.push(el);
                        break;
                        case '(':
                            s.push(el);
                            break;
                    }
                }
            }
        }
    }
}
