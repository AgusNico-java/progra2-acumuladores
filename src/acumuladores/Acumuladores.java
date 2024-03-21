package acumuladores;

public class Acumuladores {

    /**
     * Ejercicio 1:
     * Dada una matriz de enteros y un número, y que verifique si existe alguna fila donde todos sus
     * elementos sean múltiplos del número recibido por parámetro.
     * Si la matriz está vacía o si el número no es positivo, devuelve falso.
     * @param mat
     * @param num
     * @return
     */
    public boolean todosMultiplosEnAlgunaFila(int[][] mat, int num) {
        //Si la matriz está vacía o el número no es positivo, devolvemos falso
        if (mat.length == 0 || num < 1) {
            return false;
        }

        for (int fila = 0; fila < mat.length; fila++) {

            /*
             * Si el metodo todosMultiplos devuelve verdadero, se devuelve verdadero
             * (una fila es suficiente para cumplir la condición);
             * caso contrario, seguimos iterando en las siguientes filas.
             */
            if (todosMultiplos(mat[fila], num)) {
                return true;
            }
        }
        return false;
    }

    private boolean todosMultiplos(int[] fila, int num) {
        //Iniciamos el acumulador booleano
        boolean todosMultiplos = true;

        for (int i = 0; i < fila.length; i++) {
            //Determinamos si el elemento es múltiplo y operamos
            todosMultiplos = todosMultiplos && (fila[i] % num == 0);
        }

        /*
         *El acumulador será verdadero solo si cada elemento de la fila es múltiplo.
         */
        return todosMultiplos;
    }

    /**
     * Dadas 2 matrices se verifica si hay intersección entre las filas de cada matriz, fila a fila.
     * Si las matrices tienen distinta cantidad de filas o si alguna matriz está vacía, devuelve falso.
     *
     * @param mat1
     * @param mat2
     * @return
     */
    public boolean hayInterseccionPorFila(int[][] mat1, int[][]mat2) {

        //Si las matrices tienen distinta cantidad de filas, o si alguna está vacía, devolvemos false
        if (mat1.length != mat2.length || mat1.length == 0 || mat2.length == 0) {
            return false;
        }

        //tomamos cada una de las filas
        for (int fila = 0; fila < mat1.length; fila++) {
            //Si para alguna fila no existe intersección, devolvemos false.
            if (!existeInterseccion(mat1[fila], mat2[fila])) {
                return false;
            }
        }
        //Si salimos del bucle sin devolver false, quiere decir que en todas las filas hay intersección.
        return true;
    }

    private boolean existeInterseccion(int[] array1, int[] array2) {
        //Inicializamos un acumulador booleano
        boolean existe = false;

        /*
         * Si alguno de los numeros del array1 se encuentra en el array2, la intersección
         * tendrá un elemento y el acumulador pasará a ser verdadero hasta el final de las iteraciones
         */
        for (int i = 0; i < array1.length; i++) {
            existe = existe || existeNumEnArray(array1[i], array2);
        }
        return existe;
    }

    private boolean existeNumEnArray(int num, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) return true;
        }
        return false;
    }


    /**
     * Dada una matriz y el índice de una columna, se verifica si existe alguna fila cuya suma de todos sus
     * elementos sea mayor estricto que la suma de todos los elementos de la columna indicada por parámetro.
     * Si el índice de la columna es inválido o la matriz está vacía, devuelve falso
     * @param mat
     * @param nColum
     * @return
     */
    public boolean algunaFilaSumaMasQueLaColumna(int[][] mat, int nColum) {
        boolean existeFilaConSumaMayor = false;

        //Si el numero de columna es inválido o la matriz está vacía, devolvemos false
        if (mat.length == 0 || nColumInvalido(mat, nColum)) {
            return false;
        }

        //Calculamos el valor de la suma de la columna. Este valor será constante
        int sumaColumna = sumarElementosDeColumna(mat, nColum);

        /*
         * Calculamos la suma de los elementos de cada fila. Si alguno es mayor que la suma de elementos de la columna
         * el acumulador será verdadero hasta el final del método.
         */
        for (int fila = 0; fila < mat.length; fila++) {

            int sumaFila = sumarElementosDeArray(mat[fila]);
            existeFilaConSumaMayor = existeFilaConSumaMayor || (sumaFila > sumaColumna);

        }

        return existeFilaConSumaMayor;
    }

    private boolean nColumInvalido(int[][] mat, int nColum) {
        return nColum >= mat[0].length || nColum < 0;
    }

    private int sumarElementosDeArray(int[] array) {
        int suma = 0;

        for (int i = 0; i < array.length; i++) {
            suma = suma + array[i];
        }
        return suma;
    }

    private int sumarElementosDeColumna(int[][] mat, int nColum) {
        int suma = 0;
        for (int i = 0; i < mat.length; i++) {
            suma = suma + mat[i][nColum];
        }
        return suma;
    }

    /**
     * Dadas 2 matrices se verifica si hay intersección entre las columnas de cada matriz, columna a
     * columna.
     * Si las matrices tienen distinta cantidad de columnas o alguna matriz está vacía, devuelve falso
     * @param mat1
     * @param mat2
     * @return
     */
    public boolean hayInterseccionPorColumna(int[][] mat1, int[][]mat2) {
        //Si alguna de las matrices está vacía, o si tienen distintas cantidades de columnas, devolvemos false.
        if (mat1.length == 0 || mat2.length == 0 || mat1[0].length != mat2[0].length) {
            return false;
        }

        for (int col = 0; col < mat1[0].length; col++) {
            //Generamos un array con cada columna
            int[] columnaMatriz1 = formarArrayConColumna(mat1, col);
            int[] columnaMatriz2 = formarArrayConColumna(mat2, col);

            //Si para alguna columna no existe intersección, devolvemos false.
            if (!existeInterseccion(columnaMatriz1, columnaMatriz2)) {
                return false;
            }
        }
        return true;
    }

    private int[] formarArrayConColumna(int[][] mat, int col) {
        int[] array = new int[mat.length];

        for (int i = 0; i < mat.length; i++) {
            array[i] = mat[i][col];
        }
        return array;
    }


}
