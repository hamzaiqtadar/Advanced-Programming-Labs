#include <vector>
#include <stdio.h>
#include <stdlib.h>
#include <iostream>
using namespace std;

void multiply(vector<vector<int>> a, vector<vector<int>> b, int N) {
	
	vector<vector<int>> c;
	c.resize(N);
	for (int i = 0; i < N; ++i)
		c[i].resize(N);
	
	//int[][] c = new int[rowsInA][columnsInB];
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			for (int k = 0; k < N; k++) {
				c[i][j] = c[i][j] + (a[i][k] * b[k][j]);
			}
		}
	}

	cout << "Product of A and B using iterative method is"<<endl;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << c[i][j]<<"   ";
		}
		cout << endl;
	}
}


void split(vector<vector<int>> P, vector<vector<int>> C, int iB, int jB)
{
	for (int i1 = 0, i2 = iB; i1 < C.size(); i1++, i2++)
	{
		for (int j1 = 0, j2 = jB; j1 < C.size(); j1++, j2++)
			C[i1][j1] = P[i2][j2];
	}
}
/** Function to join child matrices intp parent matrix **/
void join(vector<vector<int>> C, vector<vector<int>> P, int iB, int jB)
{
	for (int i1 = 0, i2 = iB; i1 < C.size(); i1++, i2++)
	for (int j1 = 0, j2 = jB; j1 < C.size(); j1++, j2++)
		P[i2][j2] = C[i1][j1];
}
vector<vector<int>> sub(vector<vector<int>> A, vector<vector<int>> B)
{
	int n = A.size();
	vector<vector<int>> C;
	C.resize(n);
	for (int i = 0; i < n; ++i)
		C[i].resize(n);
	for (int i = 0; i < n; i++)
	for (int j = 0; j < n; j++)
		C[i][j] = A[i][j] - B[i][j];
	return C;
}
/** Function to add two matrices **/
vector<vector<int>> add(vector<vector<int>> A, vector<vector<int>> B)
{
	int n = A.size();
	vector<vector<int>> C;
	C.resize(n);
	for (int i = 0; i < n; ++i)
		C[i].resize(n);
	for (int i = 0; i < n; i++)
	for (int j = 0; j < n; j++)
		C[i][j] = A[i][j] + B[i][j];
	return C;
}

vector<vector<int>> iterativeMultiply(vector<vector<int>> A, vector<vector<int>> B)
{
	int n = A.size();
	vector<vector<int>> R;
	R.resize(n);
	for (int i = 0; i < n; ++i)
		R[i].resize(n);

	/** base case **/
	if (n == 1)
		R[0][0] = A[0][0] * B[0][0];
	else
	{
		vector<vector<int>> A11;
		A11.resize(n/2);
		for (int i = 0; i < n/2; ++i)
			A11[i].resize(n/2);
		vector<vector<int>> A12;
		A12.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			A12[i].resize(n / 2);
		vector<vector<int>> A21;
		A21.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			A21[i].resize(n / 2);
		vector<vector<int>> A22;
		A22.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			A22[i].resize(n / 2);
		vector<vector<int>> B11;
		B11.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			B11[i].resize(n / 2);
		vector<vector<int>> B12;
		B12.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			B12[i].resize(n / 2);
		vector<vector<int>> B21;
		B21.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			A21[i].resize(n / 2);
		vector<vector<int>> B22;
		B22.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			B22[i].resize(n / 2);
		

		/** Dividing matrix A into 4 halves **/
		split(A, A11, 0, 0);
		split(A, A12, 0, n / 2);
		split(A, A21, n / 2, 0);
		split(A, A22, n / 2, n / 2);
		/** Dividing matrix B into 4 halves **/
		split(B, B11, 0, 0);
		split(B, B12, 0, n / 2);
		split(B, B21, n / 2, 0);
		split(B, B22, n / 2, n / 2);

		vector<vector<int>> M1;
		M1.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			M1[i].resize(n / 2);
		vector<vector<int>> M2;
		M2.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			M2[i].resize(n / 2);
		vector<vector<int>> M3;
		M3.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			M3[i].resize(n / 2);
		vector<vector<int>> M4;
		M4.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			M4[i].resize(n / 2);
		vector<vector<int>> M5;
		M5.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			M5[i].resize(n / 2);
		vector<vector<int>> M6;
		M6.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			M6[i].resize(n / 2);
		vector<vector<int>> M7;
		M7.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			M7[i].resize(n / 2);
		M1 = iterativeMultiply(add(A11, A22), add(B11, B22));
		M2 = iterativeMultiply(add(A21, A22), B11);
		M3 = iterativeMultiply(A11, sub(B12, B22));
		M4 = iterativeMultiply(A22, sub(B21, B11));
		M5 = iterativeMultiply(add(A11, A12), B22);
		M6 = iterativeMultiply(sub(A21, A11), add(B11, B12));
		M7 = iterativeMultiply(sub(A12, A22), add(B21, B22));


		vector<vector<int>> C11;
		C11.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			C11[i].resize(n / 2);
		vector<vector<int>> C12;
		C12.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			C12[i].resize(n / 2);
		vector<vector<int>> C21;
		C21.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			C21[i].resize(n / 2);
		vector<vector<int>> C22;
		C22.resize(n / 2);
		for (int i = 0; i < n / 2; ++i)
			C22[i].resize(n / 2);
		C11 = add(sub(add(M1, M4), M5), M7);
		C12 = add(M3, M5);
		C21 = add(M2, M4);
		C22 = add(sub(add(M1, M3), M2), M6);

		/** join 4 halves into one result matrix **/
		join(C11, R, 0, 0);
		join(C12, R, 0, n / 2);
		join(C21, R, n / 2, 0);
		join(C22, R, n / 2, n / 2);
	}
	/** return result **/
	return R;
}



void main()
{
	//iterativeMultiplication myObject;
	int N;
	cout << "Enter the order n of the matrix:";
	cin >> N;
	cout << endl;
	vector<vector<int>> a;
	a.resize(N);
	for (int i = 0; i < N; ++i)
		a[i].resize(N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			//Random r = new Random();
			int random = rand()%100 + 1;
			a[i][j] = random;
		}
	}

	cout << "A is:  "<<endl;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << a[i][j]<<"   ";
		}
		cout << endl;
	}
	cout << endl;

	vector<vector<int>> b;
	b.resize(N);
	for (int i = 0; i < N; ++i)
		b[i].resize(N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			//Random r = new Random();
			int random = rand() % 1000 + 1;
			b[i][j] = random;
		}
	}

	cout << "B is:  " << endl;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << b[i][j] << "   ";
		}
		cout << endl;
	}
	cout << endl;

	multiply(a,b,N);
	
	vector<vector<int>> c;
	c.resize(N);
	for (int i = 0; i < N; ++i)
		c[i].resize(N);
	c = iterativeMultiply(a,b);
	cout << "Straussen Method yields the following result:  " << endl;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << b[i][j] << "   ";
		}
		cout << endl;
	}
	cout << endl;
}

/*main()
{
	int m1[10][10], i, j, k, m2[10][10], mult[10][10], r1, c1, r2, c2;
	printf("Enter number of rows and columns of first matrix (less than 10)\n");
	scanf("%d%d", &r1, &c1);
	printf("Enter number of rows and columns of second matrix (less than 10)\n");
	scanf("%d%d", &r2, &c2);
	if (r2 == c1)
	{
		printf("Enter rows and columns of First matrix \n");
		printf("Row wise\n");
		for (i = 0; i<r1; i++)
		for (j = 0; j<c1; j++)
			scanf("%d", &m1[i][j]);
		printf("First Matrix is :\n");
		for (i = 0; i<r1; i++)
		{
			for (j = 0; j<c1; j++)
				printf("%d\t", m1[i][j]);
			printf("\n");
		}
		printf("Enter rows and columns of Second matrix \n");
		printf("Row wise\n");
		for (i = 0; i<r2; i++)
		for (j = 0; j<c2; j++)
			scanf("%d", &m2[i][j]);
		printf("Second Matrix is:\n");
		for (i = 0; i<r2; i++)
		{
			for (j = 0; j<c2; j++)
				printf("%d\t", m2[i][j]);
			printf("\n");
		}
		printf("Multiplication of the Matrices:\n");
		for (i = 0; i<r1; i++)
		{
			for (j = 0; j<c2; j++)
			{
				mult[i][j] = 0;
				for (k = 0; k<c1; k++)
					mult[i][j] += m1[i][k] * m2[k][j];
				printf("%d\t", mult[i][j]);
			}
			printf("\n");
		}
	}
	else
	{
		printf("Matrix multiplication cannot be done");
	}
	return 0;
}*/
