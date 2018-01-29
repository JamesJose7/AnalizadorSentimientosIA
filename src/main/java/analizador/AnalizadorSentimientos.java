package analizador;

import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class AnalizadorSentimientos {
    List<String> lstTweets;
    String lstPositivas[] = {"BIEN", "BUEN", "BUENA", "EXCELENTE", "GUSTA", "GUSTAR"};
    String lstNegativas[] = {"NO", "MALO", "MAL", "PESIMO", "FEO", "ASCO"};
    int positivas;
    int negativas;
    int total_sent;
    double prc_pos, prc_neg;
    String predominante ;

    public AnalizadorSentimientos(List<String> lstTweets) {
        this.lstTweets = lstTweets;
    }

    public String analizarSentimientos() {
        String tweet;

        Iterator iter = lstTweets.iterator();

        while (iter.hasNext()) {
            tweet = (String) iter.next();

            StringTokenizer st = new StringTokenizer(tweet);
            //System.out.println("tokens count: " + st.countTokens());

            while (st.hasMoreElements()) {
                String token = st.nextElement().toString();
                token = token.toUpperCase();
                //System.out.println("token = " + token);

                for (int i = 0; i < lstPositivas.length; i++) {
                    if (token.equals(lstPositivas[i])) {
                        positivas = positivas+ 1;
                    }
                }
                for (int i = 0; i < lstNegativas.length; i++) {
                    if (token.equals(lstNegativas[i])) {
                        negativas = negativas+ 1;
                    }
                }

            }

        }
        total_sent = positivas + negativas;
        if (total_sent > 0) {
            prc_pos = (positivas * 100) / total_sent;
            prc_neg = (negativas * 100) / total_sent;
        }
        if (prc_pos > prc_neg){
            predominante = "POSITIVO";
        } else if (prc_pos < prc_neg) {
            predominante = "NEGATIVO";
        } else {
            predominante = "NEUTRAL";
        }

        return predominante;
    }

    public double getPrc_neg() {
        return prc_neg;
    }

    public double getPrc_pos() {
        return prc_pos;
    }

    public String getPredominante() {
        return predominante;
    }
}