package logica.empresas;

import excepciones.PersistenciaException;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Vector;

import persistencia.Transaccion;

public class Empresas {
	public Empresa buscarEmpresa(Transaccion t, long rut) throws PersistenciaException {
		/* 17 */String nombre = null;
		/* 18 */String direccion = null;
		/* 19 */String telefonos = null;
		/* 20 */Empresa emp = null;
		try {
			/* 23 */PreparedStatement prep = t
			/* 24 */.prepareStatement("SELECT * FROM empresas WHERE RUT = ?;");

			/* 26 */prep.setLong(1, rut);
			/* 27 */ResultSet rs = prep.executeQuery();

			/* 30 */if (rs.next()) {
				/* 31 */nombre = rs.getString(2);
				/* 32 */direccion = rs.getString(3);
				/* 33 */telefonos = rs.getString(4);
				/* 34 */emp = new Empresa(nombre, direccion, telefonos, rut);
			}
		} catch (SQLException e) {
			/* 38 */throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			/* 40 */throw new PersistenciaException(e.getMessage());
		}

		/* 43 */return emp;
	}

	public Empresa buscarEmpresaPorId(Transaccion t, int id) throws PersistenciaException {
		/* 49 */String nombre = null;
		/* 50 */String direccion = null;
		/* 51 */String telefonos = null;
		/* 52 */int rut = 0;
		/* 53 */Empresa emp = null;
		try {
			/* 56 */PreparedStatement prep = t.prepareStatement("SELECT * FROM empresas WHERE id = ?;");

			/* 58 */prep.setInt(1, id);
			/* 59 */ResultSet rs = prep.executeQuery();

			/* 62 */if (rs.next()) {
				/* 63 */nombre = rs.getString(2);
				/* 64 */direccion = rs.getString(3);
				/* 65 */telefonos = rs.getString(4);
				/* 66 */rut = rs.getInt(5);
				/* 67 */emp = new Empresa(nombre, direccion, telefonos, rut);
			}
		} catch (SQLException e) {
			/* 71 */throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			/* 73 */throw new PersistenciaException(e.getMessage());
		}

		/* 76 */return emp;
	}

	public DataEmpresa buscarDataEmpresa(Transaccion t, long rut) throws PersistenciaException {
		/* 82 */String nombre = null;
		/* 83 */String direccion = null;
		/* 84 */String telefonos = null;
		/* 85 */Empresa emp = null;
		/* 86 */DataEmpresa dataEmpresa = null;
		try {
			/* 89 */PreparedStatement prep = t.prepareStatement("SELECT * FROM empresas WHERE RUT = ?;");

			/* 91 */prep.setLong(1, rut);
			/* 92 */ResultSet rs = prep.executeQuery();

			/* 95 */if (rs.next()) {
				/* 96 */nombre = rs.getString(2);
				/* 97 */direccion = rs.getString(3);
				/* 98 */telefonos = rs.getString(4);
				/* 99 */emp = new Empresa(nombre, direccion, telefonos, rut);
				dataEmpresa = new DataEmpresa(rs.getInt(1), emp);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return dataEmpresa;
	}

	public DataEmpresa buscarDataEmpresa(Transaccion t, String nombreEmpresa) throws PersistenciaException {
		String nombre = null;
		String direccion = null;
		String telefonos = null;
		Empresa emp = null;
		DataEmpresa dataEmpresa = null;
		long rut = 0L;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM Empresas WHERE nombre = ?;");

			prep.setString(1, nombreEmpresa);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				nombre = rs.getString(2);
				direccion = rs.getString(3);
				telefonos = rs.getString(4);
				rut = rs.getLong(5);

				emp = new Empresa(nombre, direccion, telefonos, rut);
				dataEmpresa = new DataEmpresa(rs.getInt(1), emp);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return dataEmpresa;
	}

	public void insertarEmpresa(Transaccion t, Empresa emp) throws PersistenciaException {
		PreparedStatement prep = t.prepareStatement("INSERT INTO empresas (nombre, direccion, telefonos, RUT) VALUES (?,?,?,?);");
		try {
			prep.setString(1, emp.getNombre());
			prep.setString(2, emp.getDireccion());
			prep.setString(3, emp.getTelefono());
			prep.setLong(4, emp.getRUT());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public int insertarEmpresaInt(Transaccion t, Empresa emp) throws PersistenciaException {
		int key = 0;
		ResultSet rs = null;
		PreparedStatement prep = t.prepareStatement("INSERT INTO empresas (nombre, direccion, telefonos, RUT) VALUES (?,?,?,?);");
		try {
			prep.setString(1, emp.getNombre());
			prep.setString(2, emp.getDireccion());
			prep.setString(3, emp.getTelefono());
			prep.setLong(4, emp.getRUT());

			if (prep.executeUpdate() != 1) {
				throw new PersistenciaException("No ha sido posible realizar la operación");
			}
			rs = prep.getGeneratedKeys();

			if (rs.next()) {
				key = rs.getInt(1);
			}
			rs.close();
			prep.close();
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
		return key;
	}

	public void modificarEmpresa(Transaccion t, Empresa emp) throws PersistenciaException {
		PreparedStatement prep = t.prepareStatement("UPDATE empresas SET direccion = ?, telefonos = ?, RUT = ? WHERE nombre = ? ;");
		try {
			prep.setString(4, emp.getNombre());
			prep.setString(1, emp.getDireccion());
			prep.setString(2, emp.getTelefono());
			prep.setLong(3, emp.getRUT());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public void eliminarEmpresa(Transaccion t, Empresa emp) throws PersistenciaException {
		PreparedStatement prep = t.prepareStatement("DELETE FROM empresas WHERE nombre = ?;");
		try {
			prep.setString(1, emp.getNombre());

			if (prep.executeUpdate() != 1)
				throw new PersistenciaException("No ha sido posible realizar la operación");
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}
	}

	public Empresa buscarEmpresa(Transaccion t, String nombreEmpresa) throws PersistenciaException {
		Empresa emp = null;
		String nombre = null;
		String direccion = null;
		String telefonos = null;
		long rut = 0L;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM empresas WHERE nombre = ?;");

			prep.setString(1, nombreEmpresa);
			ResultSet rs = prep.executeQuery();

			if (rs.next()) {
				nombre = rs.getString(2);
				direccion = rs.getString(3);
				telefonos = rs.getString(4);
				rut = rs.getLong(5);
				emp = new Empresa(nombre, direccion, telefonos, rut);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return emp;
	}

	public Vector<Empresa> listar(Transaccion t) throws PersistenciaException {
		Vector vec = new Vector();

		String nombre = null;
		String direccion = null;
		String telefonos = null;
		long rut = 0L;
		try {
			PreparedStatement prep = t.prepareStatement("SELECT * FROM empresas ORDER BY nombre;");

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {
				nombre = rs.getString(2);
				direccion = rs.getString(3);
				telefonos = rs.getString(4);
				rut = rs.getLong(5);
				Empresa emp = new Empresa(nombre, direccion, telefonos, rut);
				vec.add(emp);
			}
		} catch (SQLException e) {
			throw new PersistenciaException(e.getMessage());
		} catch (PersistenciaException e) {
			throw new PersistenciaException(e.getMessage());
		}

		return vec;
	}

}

/*
 * Location: C:\Users\Sole\Desktop\servidor\ Qualified Name: logica.empresas.Empresas JD-Core Version: 0.6.0
 */