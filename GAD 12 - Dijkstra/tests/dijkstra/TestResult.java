package tests.dijkstra;

import gad.dijkstra.Result;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class TestResult implements Result {

	private List<Log> log;

	public static class Log {

		private int id;

		public Log(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Log log = (Log) o;
			return id == log.id;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public String toString() {
			return "Log{" +
					"id=" + id +
					'}';
		}
	}

	public static class NodeLog extends Log {

		private int length;

		public NodeLog(int id, int length) {
			super(id);
			this.length = length;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			if (!super.equals(o)) return false;
			NodeLog nodeLog = (NodeLog) o;
			return length == nodeLog.length;
		}

		@Override
		public int hashCode() {
			return Objects.hash(super.hashCode(), length);
		}

		@Override
		public String toString() {
			return "NodeLog{" +
					"length=" + length +
					'}';
		}
	}

	public TestResult() {
		log = new ArrayList<>();
	}

	@Override
	public void addNode(int id, int pathLength) {

		log.add(new NodeLog(id, pathLength));
	}

	@Override
	public void addNeighbour(int id) {

		log.add(new Log(id));
	}

	@Override
	public void addNeighbours(Collection<Integer> ids) {

	}

	public List<Log> getLog() {
		return log;
	}

	public void setLog(List<Log> log) {
		this.log = log;
	}
}
