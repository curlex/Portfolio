require_relative 'oracle'
require 'test/unit'
class OracleTest<Test::Unit::TestCase
  def set_up

    @oracle = Oracle.new (1..100)
  end
  def test_is_it

    @oracle.is_it 5
    assert(@oracle)
  end
end