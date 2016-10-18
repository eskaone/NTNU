class RelationProperties {
    /*
     * Assuming that a two column array containing the relation and a one column * array containing the set the relation is on is given in each method.
     * No checks are performed.
     */

    public static boolean isReflexive(char[][] relation, char [] set){
        int count = 0;
        for (int i = 0; i < relation.length; i++) {
            if(relation[i][0] == relation[i][1]){
                count++;
            }
        }
        return count == set.length;
    }

    public static boolean isSymmetric(char[][] relation, char [] set){
        int a = 0;
        int b = 0;
        for (int i = 0; i < relation.length;i++) {
            char[] curr = relation[i];
            if(curr[0] != curr[1]){
                a++;
                for (int j = 0; j < relation.length; j++) {
                    if(relation[j][0] != relation[j][1]) {
                        if(curr[0] == relation[j][1] && curr[1] == relation[j][0]){
                            b++;
                        }
                    }
                }
            }
        }
        return a == b;
    }

    public static boolean isTransitive(char[][] relation, char [] set){
        boolean transitive = true;
        for (int i = 0; i < relation.length; i++) {
            char x0 = relation[i][0];
            char x1 = relation[i][1];
            if(x0 != x1){
                for (int j = 0; j < relation.length; j++) {
                    char y0 = relation[j][0];
                    char y1 = relation[j][1];
                    if (y0 != y1) {
                        if (x1 == y0 && y1 != x0) {
                            int count = 0;
                            transitive = false;
                            while(count < relation.length && !transitive) {
                                char z0 = relation[count][0];
                                char z1 = relation[count][1];
                                if(z0 != z1) {
                                    if (x0 == z0) {
                                        transitive = (y1 == z1);
                                    }
                                }
                                count++;
                            }
                        }
                    }
                }
            }
        }
        if(isSymmetric(relation, set)) {
            return isReflexive(relation, set) && transitive;
        }
        return transitive;
    }

    public static boolean isAntiSymmetric(char[][] relation, char [] set){
        for (int i = 0; i < relation.length;i++) {
            char[] curr = relation[i];
            if(curr[0] != curr[1]){
                for (int j = 0; j < relation.length; j++) {
                    if(relation[j][0] != relation[j][1]) {
                        if(curr[0] == relation[j][1] && curr[1] == relation[j][0]){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean isEquivalenceRelation(char[][] relation, char [] set){
        return isReflexive(relation,set) && isSymmetric(relation,set) && isTransitive(relation,set);
    }

    public static boolean isPartialOrder(char[][] relation, char [] set){
        return isTransitive(relation,set) && isAntiSymmetric(relation, set) && isReflexive(relation, set);
    }

    public static void main(String[] args) {
        char[] setA = {'a','x','r','m','2','0'};
        char[][] rel1 = {{'a','a'},{'r','a'},{'a','2'},{'x','x'},{'r','2'},{'r','r'},{'m','m'},{'2','r'},{'0','0'},{'a','r'},{'2','2'},{'2','a'}};
        char[][] rel2 = {{'a','x'},{'r','2'},{'0','0'},{'m','2'}};
        System.out.println("Rel1 is reflexive: " + isReflexive(rel1, setA));
        System.out.println("Rel2 is reflexive: " + isReflexive(rel2, setA));
        System.out.println("Rel1 is symmetric: " + isSymmetric(rel1, setA));
        System.out.println("Rel2 is symmetric: " + isSymmetric(rel2, setA));
        System.out.println("Rel1 is transitive: " + isTransitive(rel1, setA));
        System.out.println("Rel2 is transitive: " + isTransitive(rel2, setA));
        System.out.println("Rel1 is antisymmetric: " + isAntiSymmetric(rel1, setA));
        System.out.println("Rel2 is antisymmetric: " + isAntiSymmetric(rel2, setA));
        System.out.println("Rel1 is an equivalence relation: " + isEquivalenceRelation(rel1, setA));
        System.out.println("Rel2 is an equivalence relation: " + isEquivalenceRelation(rel2, setA));
        System.out.println("Rel1 is a partial order: " + isPartialOrder(rel1, setA));
        System.out.println("Rel2 is a partial order: " + isPartialOrder(rel2, setA));
		/* skal gi følgende utskrift:
		   Rel1 is reflexive: true
		   Rel2 is reflexive: false
		   Rel1 is symmetric: true
		   Rel2 is symmetric: false
		   Rel1 is transitive: true
		   Rel2 is transitive: true
		   Rel1 is antisymmetric: false
		   Rel2 is antisymmetric: true
		   Rel1 is an equivalence relation: true
		   Rel2 is an equivalence relation: false
		   Rel1 is a partial order: false
		   Rel2 is a partial order: false
		 */
    }
}