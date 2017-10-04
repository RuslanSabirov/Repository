using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using System.Drawing;

namespace LabSystem
{
    /// <summary>
    /// класс для операций с матрицей
    /// </summary>
    class Matrix
    {
        public int size;
        public double[][] element;


        public Matrix( double[][] matrix) // Коструктор
        {
            size = matrix.Length;
            element = matrix;
        }

        public static Vector operator *(Matrix matrix, Vector vector) {
            double[] newVector = new double[vector.element.Length];
            for (int i = 0; i < vector.element.Length; i++)
            {
                newVector[i] = 0;
                for (int j = 0; j < vector.element.Length; j++)
                    newVector[i] += vector.element[j] * matrix.element[i][j];
            }
            return new Vector(newVector);
        }

        public override string ToString()  // Представление матрицы в виде строки
        {
            string str = "";
            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                    str += element[i][j].ToString() + "\t";
                str += "\r\n";
            }

            return str;
        }
        public bool SimMatrix() {
            for (int i = 0; i < size; i++) {
                for (int j = i+1; j < size; j++) {
                    if (element[i][j] != element[j][i])
                        return false;
                }
            }
            return true;
        }
        public Matrix SilMinor(int size0) {
            double[][] newElem = new double[size0][];
            for (int j = 0; j < size0; j++) {
                newElem[j] = new double[size0];
                for (int i = 0; i < size0; i++) {
                    newElem[j][i] = element[j][i];
                }
            }
            return new Matrix(newElem);
        }

        public bool SilMatrix() {
            
            for (int i = 1; i < size+1; i++) {
                if (SilMinor(i).Determinant() <= 0)
                    return false;
            }
            return true;
        }

        // Нахождение минора
        private Matrix Minor( int row, int col)
        {
            int i0 = 0, j0 = 0;
            double[][] newMatr = new double[size-1][];
            for (int j = 0; j < size; j++) 
                if (j != row) {
                    newMatr[j0] = new double[size - 1];
                    
                    for (int i = 0; i < size; i++) {
                        if (i != col) {
                            newMatr[j0][i0] = element[j][i];
                            i0++;
                        }
                    }
                    i0 = 0;
                    j0++;
                }
            return new Matrix(newMatr);
        }

        // Нахождение определителя введённой матрицы
        public double Determinant()
        {
             
            if (size == 0) return 0;
            if (element.Length!= element[0].Length) return 0;

            if (size == 1) return element[0][0];
            if (size == 2) return element[0][0]*element[1][1]-element[0][1]*element[1][0];

            double sumDet = 0;

            for (int i = 0; i < size; i++) {
                sumDet += element[0][i]*this.Minor(0,i).Determinant()*Math.Pow(-1,i);
            }

            return sumDet;
        }

    }
}
