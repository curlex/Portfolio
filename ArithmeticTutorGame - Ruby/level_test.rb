require_relative 'level.rb'
require 'test/unit'
class LevelTest <Test::Unit::TestCase
  def setup
    @level = Level.new 6,2
  end
  def test_advance_a_level
    3.times do
      @level.advance_a_level
    end
    assert(@level.level==5,'Advance a level method not working')
    @level.advance_a_level
    @level.advance_a_level
    assert(@level.level==6,'Advance level is increasing above the max level')
  end
  def test_drop_a_level
    @level.drop_a_level
    assert(@level.level==2,'Drop a level method drops below minimum level')
    @level.advance_a_level
    @level.advance_a_level
    @level.drop_a_level
    assert(@level.level==3,'Drop a level is not working properly')
  end
end