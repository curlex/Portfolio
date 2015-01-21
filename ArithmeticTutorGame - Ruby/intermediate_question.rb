require_relative 'question.rb'
class IntermediateQuestion < Question
  def initialize
    @range = (10..99)
    @operation_one = '+'
    @operation_two = '-'
    super
  end

end