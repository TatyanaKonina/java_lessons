
public class Matrix {
    private final int m;             // number of rows
    private final int n;             // number of columns
    private final Complex[][] matrix;

    public Matrix(int m, int n) {
        this.m = m;
        this.n = n;
        matrix = new Complex[m][n];
    }
    public Matrix(Complex[][] object) {
        m = object.length;
        n = object[0].length;
        this.matrix = new Complex[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++){
                    this.matrix[i][j] = object[i][j];
            }
    }
    public Matrix addition(Matrix object) {
        Matrix a = this;
        if (object.m != a.m || object.n != a.n) 
            throw new RuntimeException("Error");
        Matrix tmp = new Matrix(m, n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                tmp.matrix[i][j] = a.matrix[i][j].addition(object.matrix[i][j]);
        return tmp;
    }
    public Matrix subtraction(Matrix object) {
        Matrix a = this;
        if (object.m != a.m || object.n != a.n) 
            throw new RuntimeException("Error");
        Matrix tmp = new Matrix(m, n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                tmp.matrix[i][j] = a.matrix[i][j].subtraction(object.matrix[i][j]);
        return tmp;
    }
    public Matrix transpose() {
        Matrix tmp = new Matrix(n, m);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                tmp.matrix[j][i] = this.matrix[i][j];
        return tmp;
    }
    public static Matrix subMatrix(Matrix object, int exclude_m, int exclude_n) {
        Matrix result = new Matrix(object.m - 1, object.n - 1);
        for (int i = 0, p = 0; i < object.m; ++i) {
            if (i != exclude_m - 1) {
                for (int j = 0, q = 0; j < object.n; ++j) {
                    if (j != exclude_n - 1) {
                        result.matrix[p][q] = object.matrix[i][j];
                        ++q;
                    }
                }
                ++p;
            }
        }
        return result;
    }

    public Complex determinant() {
        if (m != n) {
            throw new RuntimeException("Error");
        }
        else {
            return _determinant(this);
        }
    }
    private Complex _determinant(Matrix object) {
            if (object.n == 1) {
                return object.matrix[0][0];
            } 
            else if (object.n == 2) {
                Complex tmp1 = object.matrix[0][0].multiplication(object.matrix[1][1]);
                Complex tmp2 = object.matrix[0][1].multiplication(object.matrix[1][0]);
                return tmp1.subtraction(tmp2);
            }
            else {
                Complex result = new Complex();
                for (int i = 0; i < object.n; ++i) {
                    Matrix sub = subMatrix(object, 1, i + 1);
                    Complex tmp = object.matrix[0][i].scale(Math.pow(-1, 1 + i + 1)).multiplication(_determinant(sub));
                    result = result.addition(tmp);
                }
                return result;
            }
        }
    public void printMatrix() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) 
                System.out.print(matrix[i][j]+ " ");
            System.out.println();
        }
    }
}
